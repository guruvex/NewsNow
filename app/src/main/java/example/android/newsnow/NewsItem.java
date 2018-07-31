package example.android.newsnow;

/**
 * Created by james on 7/31/2018.
 */

public class NewsItem {

    private String mTitle;
    private int mImage;

    public NewsItem (String title, int image){
        mTitle = title;
        mImage = image;
        }

        public String getTitle(){return mTitle;}
        public int getImage(){return mImage;}
}


