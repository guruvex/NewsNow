package example.android.newsnow;

/**
 * Created by james on 7/31/2018.
 */

public class NewsItem {

    private String mTitle;
    private int mImage;
    private String mWebURL;
    private String mSectionName;

    public NewsItem (String title, int image, String webURL, String sectionName){
        mTitle = title;
        mImage = image;
        mWebURL = webURL;
        mSectionName = sectionName;
        }

        public String getTitle(){return mTitle;}
        public int getImage(){return mImage;}
        public String getWebURL(){return mWebURL;}
        public String getSectionName(){return mSectionName;}
}


