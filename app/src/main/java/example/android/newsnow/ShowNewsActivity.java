package example.android.newsnow;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class ShowNewsActivity extends AppCompatActivity {
    TextView mTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_news);
        mTextView = findViewById(R.id.news_update);
        mTextView.setVisibility(View.GONE);
        GetUrlContentTask task = new GetUrlContentTask();
        task.execute();
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
                mTextView.setVisibility(View.VISIBLE);
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
