package example.android.newsnow;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class ShowNewsActivity extends AppCompatActivity {

    final ArrayList<NewsItem> newsItem = new ArrayList<>();
    TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_news);

        mTextView = findViewById(R.id.news_update);

        GetUrlContentTask task = new GetUrlContentTask();
        task.execute();

        // this list will be replaced by url feed

        //newsItem.add(new NewsItem("test", R.drawable.blackcat));
        //newsItem.add(new NewsItem("test", R.drawable.blackcat));
        //newsItem.add(new NewsItem("test", R.drawable.blackcat));
        //newsItem.add(new NewsItem("test", R.drawable.blackcat));
        //newsItem.add(new NewsItem("test", R.drawable.blackcat));
        //newsItem.add(new NewsItem("test", R.drawable.blackcat));
        //newsItem.add(new NewsItem("test", R.drawable.blackcat));
        //newsItem.add(new NewsItem("test", R.drawable.blackcat));



    }
    private URL createUrl(String stringUrl) {
        // turns the passed string to a url the HttpConnection can handle
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException exception) {
            mTextView.setText("something went wrong");
            return null;
        }
        return url;
    }

    public class GetUrlContentTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {

            // get url from last activity
            Bundle bundle = getIntent().getExtras();
            String newURL = bundle.getString("url");

            // testing if i get the url passed
            mTextView.setText(newURL);

            HttpConnection getNews = new HttpConnection();
            String jsonString = "";
            try {
                jsonString = getNews.makeHttpRequest(createUrl(newURL));

            } catch (IOException e) {
                return null;
            }

            /**
             * if it worked to this point process the
             * raw jason data is stored in jsonString
             */
            StringBuilder data = new StringBuilder(); // string to hold the processed json data

            try {
                JSONObject rootJsonObj = new JSONObject(jsonString); // build root jason obj
                JSONArray myJsonArray = rootJsonObj.optJSONArray("results"); // build array from the root object

                for (int i = 0; i < myJsonArray.length(); i++) {
                    JSONObject jsonItems = myJsonArray.getJSONObject(i); // pull out each item in the jason array one at a time.

                    String sectionName = jsonItems.optString("sectionName").toString();
                    String webTitle = jsonItems.optString("webTitle").toString();
                    String webUrl = jsonItems.optString("webUrl").toString();

                    data.append(sectionName);
                    data.append(webTitle);
                    data.append(webUrl);

                    //newsItem.add(new NewsItem(webTitle, R.drawable.blackcat,webUrl,sectionName));

                }} catch (Exception e) {
                System.out.println("error " + e);
            }
            return data.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            TextView mTextView = findViewById(R.id.news_update);
            mTextView.setText(s);

            NewsViewAdaptor displayNewsAdapter = new NewsViewAdaptor (ShowNewsActivity.this, newsItem);
            ListView listView = (ListView) findViewById(R.id.list);
            listView.setAdapter(displayNewsAdapter);
        }
    }
}
