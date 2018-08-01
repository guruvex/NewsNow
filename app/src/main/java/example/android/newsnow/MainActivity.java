package example.android.newsnow;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public String URLkey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button nextPage = findViewById(R.id.find_news);


        nextPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowToast();
            }
        });
    }
    public void ShowToast () {
        // this will build the url based on user selections and pass it to the new intent
        Toast.makeText(this, R.string.finding_news, Toast.LENGTH_SHORT).show();

        RadioButton rb1 = findViewById(R.id.rb_1);
        RadioButton rb2 = findViewById(R.id.rb_2);
        RadioButton rb3 = findViewById(R.id.rb_3);
        RadioButton rb4 = findViewById(R.id.rb_4);
        // build url to pass to next activity
        // url building in simple state to test with
        if (rb1.isChecked()) {
            URLkey = "https://content.guardianapis.com/search?q=sports&api-key=a8fc710c-26a0-4c93-85b9-7319adb3f0b9";
        }
        if (rb2.isChecked()) {
            URLkey = "https://content.guardianapis.com/search?q=weather&api-key=a8fc710c-26a0-4c93-85b9-7319adb3f0b9";
        }
        if (rb3.isChecked()) {
            URLkey = "https://content.guardianapis.com/search?q=tech&api-key=a8fc710c-26a0-4c93-85b9-7319adb3f0b9";
        }
        if (rb4.isChecked()) {
            // unassigned temp url used
            URLkey = "https://content.guardianapis.com/search?q=sports&api-key=a8fc710c-26a0-4c93-85b9-7319adb3f0b9";
        }
        Intent showNews = new Intent(this, ShowNewsActivity.class);
        showNews.putExtra("url",URLkey);
        startActivity(showNews);
    }
}

