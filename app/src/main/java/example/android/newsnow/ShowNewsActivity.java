package example.android.newsnow;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class ShowNewsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_news);

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
}
