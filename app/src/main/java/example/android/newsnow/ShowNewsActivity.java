package example.android.newsnow;

import android.app.LoaderManager;
import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class ShowNewsActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String>{

    TextView mTextView;

    @Override
    public Loader<String> onCreateLoader(int i, Bundle bundle) {
        return new AsyncTaskLoader<String>(this) {
            @Override
            public String loadInBackground() {
                //Think of this as AsyncTask doInBackground() method, here you will actually initiate Network call, or any work that need to be done on background
                // get url from last activity
                Bundle bundle = getIntent().getExtras();
                String newURL = bundle.getString("url");
                HttpConnection getNews = new HttpConnection();
                String jsonString = "";
                try {
                    jsonString = getNews.makeHttpRequest(createUrl(newURL));
                } catch (IOException e) {
                   System.out.print("exception: " + e.getMessage());
                    return null;
                }
                /**
                 * if it worked to this point
                 * return the json string for processing
                 */
                return jsonString;
            }
        };
    }
    @Override
    public void onLoadFinished(Loader<String> loader, String jsonString) {

        try {
            JSONObject rootJsonObj = new JSONObject(jsonString); // build root jason obj
        } catch (IOException e) {
            mTextView.setVisibility(View.VISIBLE);
        }

        final ArrayList<NewsItem> newsItem = new ArrayList<>();
        try {
            JSONObject jsonFirstLevel = rootJsonObj.getJSONObject("response");
            JSONArray jsonDataArray = jsonFirstLevel.getJSONArray("results");
            for (int i = 0; i < jsonDataArray.length(); i++) {
                // pull out each item in the jason array one at a time.
                JSONObject jsonItems = jsonDataArray.getJSONObject(i);
                // pull the data to load in the objects.
                String webTitle = jsonItems.optString("webTitle").toString();
                String sectionName = jsonItems.optString("sectionName").toString();
                String webUrl = jsonItems.optString("webUrl").toString();
                String date = jsonItems.optString("webPublicationDate");
                // put them in the news item objects.
                newsItem.add(new NewsItem(webTitle, webUrl, sectionName, date));
            }
        } catch (Exception e) {
            mTextView.setVisibility(View.VISIBLE);
        }
        NewsViewAdaptor displayNewsAdapter = new NewsViewAdaptor(ShowNewsActivity.this, newsItem);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(displayNewsAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                NewsItem link = newsItem.get(position);
                Intent openBrowser = new Intent(Intent.ACTION_VIEW, Uri.parse(link.getWebURL()));
                startActivity(openBrowser);
            }
        });
    }

    @Override
    public void onLoaderReset(Loader<String> loader) {
        //Leave it for now as it is
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_news);
        mTextView = findViewById(R.id.news_update);
        mTextView.setVisibility(View.GONE);



        if(isConnected()) {
            GetUrlContentTask task = new GetUrlContentTask();
            task.execute();
        } else{
            Toast.makeText(this,  "there is no interent connection", Toast.LENGTH_SHORT).show();
        }




    }
    private URL createUrl(String stringUrl) {
        // turns the passed string to a url the HttpConnection can handle
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException exception) {
            mTextView.setVisibility(View.VISIBLE);;
            return null;
        }
        return url;
    }
    private boolean isConnected() {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }

    public class GetUrlContentTask extends AsyncTask<String, Void, JSONObject> {
        @Override
        protected JSONObject doInBackground(String... strings) {
            // get url from last activity
            Bundle bundle = getIntent().getExtras();
            String newURL = bundle.getString("url");
            HttpConnection getNews = new HttpConnection();
            String jsonString = "";
            try {
                jsonString = getNews.makeHttpRequest(createUrl(newURL));
            } catch (IOException e) {
                Log.d("hi","exception: "+e.getMessage());
                return null;
            }
            /**
             * if it worked to this point process the
             * raw jason data is stored in jsonString
             */
            try {
                JSONObject rootJsonObj = new JSONObject(jsonString); // build root jason obj
               return rootJsonObj;
            } catch (Exception e) {
                mTextView.setVisibility(View.VISIBLE);
            }
            return null;
        }
        @Override
        protected void onPostExecute(JSONObject rootJsonObj) {
            super.onPostExecute(rootJsonObj);
            final ArrayList<NewsItem> newsItem = new ArrayList<>();
            try {
                JSONObject jsonFirstLevel = rootJsonObj.getJSONObject("response");
                JSONArray jsonDataArray = jsonFirstLevel.getJSONArray("results");
                for (int i = 0; i < jsonDataArray.length(); i++) {
                    // pull out each item in the jason array one at a time.
                    JSONObject jsonItems = jsonDataArray.getJSONObject(i);
                    // pull the data to load in the objects.
                    String webTitle = jsonItems.optString("webTitle").toString();
                    String sectionName = jsonItems.optString("sectionName").toString();
                    String webUrl = jsonItems.optString("webUrl").toString();
                    String date = jsonItems.optString("webPublicationDate");

                    //JSONArray jsonThirdLevel = jsonDataArray.getJSONObject("tags");

                    // put them in the news item objects.
                    newsItem.add(new NewsItem(webTitle, webUrl, sectionName, date));
                }
            } catch (Exception e) {
                mTextView.setVisibility(View.VISIBLE);
            }
            NewsViewAdaptor displayNewsAdapter = new NewsViewAdaptor(ShowNewsActivity.this, newsItem);
            ListView listView = (ListView) findViewById(R.id.list);
            listView.setAdapter(displayNewsAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                    NewsItem link = newsItem.get(position);
                    Intent openBrowser = new Intent(Intent.ACTION_VIEW, Uri.parse(link.getWebURL()));
                    startActivity(openBrowser);
                }
            });
        }
    }
}
