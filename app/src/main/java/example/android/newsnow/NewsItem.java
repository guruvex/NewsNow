package example.android.newsnow;

/**
 * Created by james on 7/31/2018.
 */

public class NewsItem {

    private String mTitle;
    private String mWebURL;
    private String mSectionName;

    public NewsItem (String title, String webURL, String sectionName){
        mTitle = title;
        mWebURL = webURL;
        mSectionName = sectionName;
        }

        public String getTitle(){return mTitle;}
        public String getWebURL(){return mWebURL;}
        public String getSectionName(){return mSectionName;}
}


