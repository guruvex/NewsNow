package example.android.newsnow;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by james on 7/31/2018.
 */

public class NewsViewAdaptor extends ArrayAdapter<NewsItem> {

        private static final String LOG_TAG = NewsViewAdaptor.class.getSimpleName();

        /**
         * This is our own custom constructor (it doesn't mirror a superclass constructor).
         * The context is used to inflate the layout file, and the list is the data we want
         * to populate into the lists.
         *
         */
        public NewsViewAdaptor(Activity context, ArrayList<NewsItem> newsItems) {
            // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
            // the second argument is used when the ArrayAdapter is populating a single TextView.
            // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
            // going to use this second argument, so it can be any value. Here, we used 0.
            super(context, 0, newsItems);
        }

        /**
         * Provides a view for an AdapterView (ListView, GridView, etc.)
         *
         * @param position The position in the list of data that should be displayed in the
         *                 list item view.
         * @param convertView The recycled view to populate.
         * @param parent The parent ViewGroup that is used for inflation.
         * @return The View for the position in the AdapterView.
         */
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Check if the existing view is being reused, otherwise inflate the view
            View listItemView = convertView;
            if(listItemView == null) {
                listItemView = LayoutInflater.from(getContext()).inflate(
                        R.layout.news_item, parent, false);
            }

            // Get the {@link word item} object located at this position in the list
            NewsItem currentItem = getItem(position);

            // Find the TextView in the list_item.xml layout with the ID version_name
            TextView newsText = (TextView) listItemView.findViewById(R.id.news_story_catagory);
            // Get the version name from the current miwok object and
            // set this text on the name TextView
            newsText.setText(currentItem.getSectionName());

            TextView newsTitle = (TextView) listItemView.findViewById(R.id.news_story_title);
            // Get the version name from the current miwok object and
            // set this text on the name TextView
            newsTitle.setText(currentItem.getTitle());




            return listItemView;
        }
}

