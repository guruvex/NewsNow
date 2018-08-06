package example.android.newsnow;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

        GetUrlContentTask task = new GetUrlContentTask();
        task.execute();


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
                return null;
            }

            //jsonString = "{\"response\":{\"status\":\"ok\",\"userTier\":\"developer\",\"total\":45590,\"startIndex\":1,\"pageSize\":10,\"currentPage\":1,\"pages\":4559,\"orderBy\":\"relevance\",\"results\":[{\"id\":\"sport/live/2018/aug/04/sportwatch-showdown-45-super-rugby-final-netball-and-more\",\"type\":\"liveblog\",\"sectionId\":\"sport\",\"sectionName\":\"Sport\",\"webPublicationDate\":\"2018-08-04T12:36:44Z\",\"webTitle\":\"Sportwatch: Showdown 45, Super Rugby final, Netball - as it happened\",\"webUrl\":\"https://www.theguardian.com/sport/live/2018/aug/04/sportwatch-showdown-45-super-rugby-final-netball-and-more\",\"apiUrl\":\"https://content.guardianapis.com/sport/live/2018/aug/04/sportwatch-showdown-45-super-rugby-final-netball-and-more\",\"isHosted\":false,\"pillarId\":\"pillar/sport\",\"pillarName\":\"Sport\"},{\"id\":\"sport/2018/jul/31/after-marginal-gains-where-now-for-super-rugby-in-australia\",\"type\":\"article\",\"sectionId\":\"sport\",\"sectionName\":\"Sport\",\"webPublicationDate\":\"2018-07-31T02:36:39Z\",\"webTitle\":\"After marginal gains, where now for Super Rugby in Australia? | Bret Harris\",\"webUrl\":\"https://www.theguardian.com/sport/2018/jul/31/after-marginal-gains-where-now-for-super-rugby-in-australia\",\"apiUrl\":\"https://content.guardianapis.com/sport/2018/jul/31/after-marginal-gains-where-now-for-super-rugby-in-australia\",\"isHosted\":false,\"pillarId\":\"pillar/sport\",\"pillarName\":\"Sport\"},{\"id\":\"sport/2018/jul/24/dewsbury-rams-batley-bulldogs-local-derby\",\"type\":\"article\",\"sectionId\":\"sport\",\"sectionName\":\"Sport\",\"webPublicationDate\":\"2018-07-24T07:00:06Z\",\"webTitle\":\"Dewsbury, Batley and the most neighbourly of rugby league derbies\",\"webUrl\":\"https://www.theguardian.com/sport/2018/jul/24/dewsbury-rams-batley-bulldogs-local-derby\",\"apiUrl\":\"https://content.guardianapis.com/sport/2018/jul/24/dewsbury-rams-batley-bulldogs-local-derby\",\"isHosted\":false,\"pillarId\":\"pillar/sport\",\"pillarName\":\"Sport\"},{\"id\":\"sport/live/2018/jul/21/super-rugby-qualifying-final-waratahs-v-highlanders-live\",\"type\":\"liveblog\",\"sectionId\":\"sport\",\"sectionName\":\"Sport\",\"webPublicationDate\":\"2018-07-21T13:02:50Z\",\"webTitle\":\"Super Rugby qualifying final: Waratahs v Highlanders - as it happened\",\"webUrl\":\"https://www.theguardian.com/sport/live/2018/jul/21/super-rugby-qualifying-final-waratahs-v-highlanders-live\",\"apiUrl\":\"https://content.guardianapis.com/sport/live/2018/jul/21/super-rugby-qualifying-final-waratahs-v-highlanders-live\",\"isHosted\":false,\"pillarId\":\"pillar/sport\",\"pillarName\":\"Sport\"},{\"id\":\"sport/2018/jul/21/bradford-bulls-rebuild-rugby-league-ranks-league-1\",\"type\":\"article\",\"sectionId\":\"sport\",\"sectionName\":\"Sport\",\"webPublicationDate\":\"2018-07-21T12:00:35Z\",\"webTitle\":\"Bradford Bulls rebuild to bounce back through rugby league’s ranks\",\"webUrl\":\"https://www.theguardian.com/sport/2018/jul/21/bradford-bulls-rebuild-rugby-league-ranks-league-1\",\"apiUrl\":\"https://content.guardianapis.com/sport/2018/jul/21/bradford-bulls-rebuild-rugby-league-ranks-league-1\",\"isHosted\":false,\"pillarId\":\"pillar/sport\",\"pillarName\":\"Sport\"},{\"id\":\"sport/2018/jul/18/sam-warburton-retires-rugby-union-29\",\"type\":\"article\",\"sectionId\":\"sport\",\"sectionName\":\"Sport\",\"webPublicationDate\":\"2018-07-18T10:57:16Z\",\"webTitle\":\"Sam Warburton announces shock retirement from rugby union aged 29\",\"webUrl\":\"https://www.theguardian.com/sport/2018/jul/18/sam-warburton-retires-rugby-union-29\",\"apiUrl\":\"https://content.guardianapis.com/sport/2018/jul/18/sam-warburton-retires-rugby-union-29\",\"isHosted\":false,\"pillarId\":\"pillar/sport\",\"pillarName\":\"Sport\"},{\"id\":\"sport/2018/jul/16/tributes-paid-to-amateur-rugby-player-who-died-mid-match\",\"type\":\"article\",\"sectionId\":\"sport\",\"sectionName\":\"Sport\",\"webPublicationDate\":\"2018-07-16T09:47:29Z\",\"webTitle\":\"Tributes paid to amateur rugby player who died mid-match\",\"webUrl\":\"https://www.theguardian.com/sport/2018/jul/16/tributes-paid-to-amateur-rugby-player-who-died-mid-match\",\"apiUrl\":\"https://content.guardianapis.com/sport/2018/jul/16/tributes-paid-to-amateur-rugby-player-who-died-mid-match\",\"isHosted\":false,\"pillarId\":\"pillar/sport\",\"pillarName\":\"Sport\"},{\"id\":\"sport/2018/jul/13/waratahs-super-rugby-revival-can-have-far-reaching-impact\",\"type\":\"article\",\"sectionId\":\"sport\",\"sectionName\":\"Sport\",\"webPublicationDate\":\"2018-07-12T18:00:49Z\",\"webTitle\":\"Waratahs’ Super Rugby revival can have far-reaching impact | Bret Harris\",\"webUrl\":\"https://www.theguardian.com/sport/2018/jul/13/waratahs-super-rugby-revival-can-have-far-reaching-impact\",\"apiUrl\":\"https://content.guardianapis.com/sport/2018/jul/13/waratahs-super-rugby-revival-can-have-far-reaching-impact\",\"isHosted\":false,\"pillarId\":\"pillar/sport\",\"pillarName\":\"Sport\"},{\"id\":\"sport/2018/jul/02/josh-hodgson-cant-save-raiders-from-dud-rugby-league\",\"type\":\"article\",\"sectionId\":\"sport\",\"sectionName\":\"Sport\",\"webPublicationDate\":\"2018-07-01T18:00:46Z\",\"webTitle\":\"Josh Hodgson can't save Raiders from dud rugby league\",\"webUrl\":\"https://www.theguardian.com/sport/2018/jul/02/josh-hodgson-cant-save-raiders-from-dud-rugby-league\",\"apiUrl\":\"https://content.guardianapis.com/sport/2018/jul/02/josh-hodgson-cant-save-raiders-from-dud-rugby-league\",\"isHosted\":false,\"pillarId\":\"pillar/sport\",\"pillarName\":\"Sport\"},{\"id\":\"sport/live/2018/jun/23/australia-v-ireland-rugby-union-international-live\",\"type\":\"liveblog\",\"sectionId\":\"sport\",\"sectionName\":\"Sport\",\"webPublicationDate\":\"2018-06-23T12:30:45Z\",\"webTitle\":\"Australia v Ireland: rugby union international – as it happened\",\"webUrl\":\"https://www.theguardian.com/sport/live/2018/jun/23/australia-v-ireland-rugby-union-international-live\",\"apiUrl\":\"https://content.guardianapis.com/sport/live/2018/jun/23/australia-v-ireland-rugby-union-international-live\",\"isHosted\":false,\"pillarId\":\"pillar/sport\",\"pillarName\":\"Sport\"}]}}";
            /**
             * if it worked to this point process the
             * raw jason data is stored in jsonString
             */
            //StringBuilder data = new StringBuilder(); // string to hold the processed json data

            try {
                JSONObject rootJsonObj = new JSONObject(jsonString); // build root jason obj

               return rootJsonObj;
            } catch (Exception e) {
                System.out.println("error " + e);
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
                    // put them in the news item objects.
                    newsItem.add(new NewsItem(webTitle, R.drawable.blackcat,webUrl,sectionName));
                }
            }  catch (Exception e) {
                System.out.println("error " + e);
            }

            NewsViewAdaptor displayNewsAdapter = new NewsViewAdaptor (ShowNewsActivity.this, newsItem);
            ListView listView = (ListView) findViewById(R.id.list);
            listView.setAdapter(displayNewsAdapter);
        }
    }
}
