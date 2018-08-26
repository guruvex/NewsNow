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
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class ShowNewsActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String>{

    TextView mTextView;
    final ArrayList<NewsItem> newsItem = new ArrayList<>();
private static final int uniqueLoderNumber = 1;

    @Override
    public Loader<String> onCreateLoader(int id, Bundle args) {
        //Here we will initiate AsyncTaskLoader and handle task in background

        // this is a test string, the createUrl method will replace this sting.
        String jsonString = "{\"response\":{\"status\":\"ok\",\"userTier\":\"developer\",\"total\":92132,\"startIndex\":1,\"pageSize\":10,\"currentPage\":1,\"pages\":9214,\"orderBy\":\"relevance\",\"results\":[{\"id\":\"sport/2018/aug/16/sports-nerds-analytics-data\",\"type\":\"article\",\"sectionId\":\"sport\",\"sectionName\":\"Sport\",\"webPublicationDate\":\"2018-08-16T09:00:06Z\",\"webTitle\":\"Are super-nerds really ruining US sports?\",\"webUrl\":\"https://www.theguardian.com/sport/2018/aug/16/sports-nerds-analytics-data\",\"apiUrl\":\"https://content.guardianapis.com/sport/2018/aug/16/sports-nerds-analytics-data\",\"isHosted\":false,\"pillarId\":\"pillar/sport\",\"pillarName\":\"Sport\"},{\"id\":\"travel/2018/aug/25/activity-hotel-viana-portugal-cycling-watersports\",\"type\":\"article\",\"sectionId\":\"travel\",\"sectionName\":\"Travel\",\"webPublicationDate\":\"2018-08-25T09:00:40Z\",\"webTitle\":\"Takes all sports: an activity break in northern Portugal\",\"webUrl\":\"https://www.theguardian.com/travel/2018/aug/25/activity-hotel-viana-portugal-cycling-watersports\",\"apiUrl\":\"https://content.guardianapis.com/travel/2018/aug/25/activity-hotel-viana-portugal-cycling-watersports\",\"isHosted\":false,\"pillarId\":\"pillar/lifestyle\",\"pillarName\":\"Lifestyle\"},{\"id\":\"sport/2018/aug/13/eleven-sports-pga-championship-brooks-koepka\",\"type\":\"article\",\"sectionId\":\"sport\",\"sectionName\":\"Sport\",\"webPublicationDate\":\"2018-08-13T13:27:47Z\",\"webTitle\":\"Eleven Sports viewers miss Brooks Koepka win US PGA Championship\",\"webUrl\":\"https://www.theguardian.com/sport/2018/aug/13/eleven-sports-pga-championship-brooks-koepka\",\"apiUrl\":\"https://content.guardianapis.com/sport/2018/aug/13/eleven-sports-pga-championship-brooks-koepka\",\"isHosted\":false,\"pillarId\":\"pillar/sport\",\"pillarName\":\"Sport\"},{\"id\":\"business/2018/aug/11/house-of-fraser-customers-react-to-sports-direct-takeover\",\"type\":\"article\",\"sectionId\":\"business\",\"sectionName\":\"Business\",\"webPublicationDate\":\"2018-08-11T07:00:25Z\",\"webTitle\":\"House of Fraser customers react to Sports Direct takeover\",\"webUrl\":\"https://www.theguardian.com/business/2018/aug/11/house-of-fraser-customers-react-to-sports-direct-takeover\",\"apiUrl\":\"https://content.guardianapis.com/business/2018/aug/11/house-of-fraser-customers-react-to-sports-direct-takeover\",\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"},{\"id\":\"football/2018/may/02/eleven-sports-la-liga-internet-live-footbal-sky-sports-radrizzani\",\"type\":\"article\",\"sectionId\":\"football\",\"sectionName\":\"Football\",\"webPublicationDate\":\"2018-05-02T11:52:41Z\",\"webTitle\":\"Sky Sports loses live La Liga rights to Leeds owner’s Eleven Sports\",\"webUrl\":\"https://www.theguardian.com/football/2018/may/02/eleven-sports-la-liga-internet-live-footbal-sky-sports-radrizzani\",\"apiUrl\":\"https://content.guardianapis.com/football/2018/may/02/eleven-sports-la-liga-internet-live-footbal-sky-sports-radrizzani\",\"isHosted\":false,\"pillarId\":\"pillar/sport\",\"pillarName\":\"Sport\"},{\"id\":\"sport/2018/aug/01/european-sports-championships-glasgow-liftoff\",\"type\":\"article\",\"sectionId\":\"sport\",\"sectionName\":\"Sport\",\"webPublicationDate\":\"2018-08-01T12:03:48Z\",\"webTitle\":\"European Sports Championships: launch pad for a new spectacle of sport\",\"webUrl\":\"https://www.theguardian.com/sport/2018/aug/01/european-sports-championships-glasgow-liftoff\",\"apiUrl\":\"https://content.guardianapis.com/sport/2018/aug/01/european-sports-championships-glasgow-liftoff\",\"isHosted\":false,\"pillarId\":\"pillar/sport\",\"pillarName\":\"Sport\"},{\"id\":\"education/2018/jun/28/spending-more-on-boys-sports-teams-breaks-law-schools-told\",\"type\":\"article\",\"sectionId\":\"education\",\"sectionName\":\"Education\",\"webPublicationDate\":\"2018-06-28T17:21:15Z\",\"webTitle\":\"Spending more on boys' sports teams breaks law, schools told\",\"webUrl\":\"https://www.theguardian.com/education/2018/jun/28/spending-more-on-boys-sports-teams-breaks-law-schools-told\",\"apiUrl\":\"https://content.guardianapis.com/education/2018/jun/28/spending-more-on-boys-sports-teams-breaks-law-schools-told\",\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"},{\"id\":\"sport/2018/jun/26/oscar-robertson-white-athletes-sports-protests\",\"type\":\"article\",\"sectionId\":\"sport\",\"sectionName\":\"Sport\",\"webPublicationDate\":\"2018-06-26T11:46:50Z\",\"webTitle\":\"Oscar Robertson on sports protests: 'Where are the white athletes?'\",\"webUrl\":\"https://www.theguardian.com/sport/2018/jun/26/oscar-robertson-white-athletes-sports-protests\",\"apiUrl\":\"https://content.guardianapis.com/sport/2018/jun/26/oscar-robertson-white-athletes-sports-protests\",\"isHosted\":false,\"pillarId\":\"pillar/sport\",\"pillarName\":\"Sport\"},{\"id\":\"lifeandstyle/2018/jun/16/league-against-cruel-sports-legal-battle-with-whistleblower\",\"type\":\"article\",\"sectionId\":\"lifeandstyle\",\"sectionName\":\"Life and style\",\"webPublicationDate\":\"2018-06-16T15:00:00Z\",\"webTitle\":\"League Against Cruel Sports in legal battle with vegan ‘whistleblower’\",\"webUrl\":\"https://www.theguardian.com/lifeandstyle/2018/jun/16/league-against-cruel-sports-legal-battle-with-whistleblower\",\"apiUrl\":\"https://content.guardianapis.com/lifeandstyle/2018/jun/16/league-against-cruel-sports-legal-battle-with-whistleblower\",\"isHosted\":false,\"pillarId\":\"pillar/lifestyle\",\"pillarName\":\"Lifestyle\"},{\"id\":\"lifeandstyle/2018/may/27/world-cycling-revival-event-herne-hill-velodrome-london-vintage\",\"type\":\"article\",\"sectionId\":\"lifeandstyle\",\"sectionName\":\"Life and style\",\"webPublicationDate\":\"2018-05-27T05:00:17Z\",\"webTitle\":\"World Cycling Revival preview: ‘An action-packed heritage sports extravaganza’ | Martin Love\",\"webUrl\":\"https://www.theguardian.com/lifeandstyle/2018/may/27/world-cycling-revival-event-herne-hill-velodrome-london-vintage\",\"apiUrl\":\"https://content.guardianapis.com/lifeandstyle/2018/may/27/world-cycling-revival-event-herne-hill-velodrome-london-vintage\",\"isHosted\":false,\"pillarId\":\"pillar/lifestyle\",\"pillarName\":\"Lifestyle\"}]}}";
        JSONObject rootjson;

        try {
            rootjson = new JSONObject(jsonString);
            JSONObject jsonFirstLevel = rootjson.getJSONObject("response");
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
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void onLoadFinished(Loader<String> loader, String data) {
        //After getting result we will update our UI here
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
            getLoaderManager().destroyLoader(uniqueLoderNumber);
            LoaderManager myLoader = getLoaderManager();
            myLoader.initLoader(uniqueLoderNumber,null, this);

            //GetUrlContentTask task = new GetUrlContentTask();
            //task.execute();

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

    /**
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
            */
            /**
             * if it worked to this point process the
             * raw jason data is stored in jsonString
             */
            /**
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
             */
}
