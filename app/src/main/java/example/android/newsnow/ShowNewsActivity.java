package example.android.newsnow;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class ShowNewsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_news);
        // ToDo get url from last activity

        doInBackground();

        // this list will be replaced by url feed
        final ArrayList<NewsItem> newsItem = new ArrayList<>();
        newsItem.add(new NewsItem("test", R.drawable.blackcat));
        newsItem.add(new NewsItem("test", R.drawable.blackcat));
        newsItem.add(new NewsItem("test", R.drawable.blackcat));
        newsItem.add(new NewsItem("test", R.drawable.blackcat));
        newsItem.add(new NewsItem("test", R.drawable.blackcat));
        newsItem.add(new NewsItem("test", R.drawable.blackcat));
        newsItem.add(new NewsItem("test", R.drawable.blackcat));
        newsItem.add(new NewsItem("test", R.drawable.blackcat));

        NewsViewAdaptor displayNewsAdapter = new NewsViewAdaptor (this, newsItem);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(displayNewsAdapter);

    }
    private URL createUrl(String stringUrl) {
        // turns the passed string to a url the HttpConnection can handle
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException exception) {
            return null;
        }
        return url;
    }

    protected String doInBackground(String... url1) {

        Bundle bundle = getIntent().getExtras();
        String newURL = bundle.getString("url");

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

                String name = jsonItems.optString("artistName").toString();
                String album = jsonItems.optString("collectionName").toString();
                String price = jsonItems.optString("collectionPrice").toString();
                String currency = jsonItems.optString("currency").toString();

            }} catch (Exception e) {
            System.out.println("error " + e);
        }
        return data.toString();
    }
}
