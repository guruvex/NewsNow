package example.android.newsnow;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

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
        Toast.makeText(this, "Find News", Toast.LENGTH_SHORT).show();

        Intent showNews = new Intent(this, ShowNewsActivity.class);
        startActivity(showNews);
    }
}

