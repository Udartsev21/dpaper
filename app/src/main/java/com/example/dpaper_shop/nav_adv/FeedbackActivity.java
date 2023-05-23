package com.example.dpaper_shop.nav_adv;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.dpaper_shop.HomeActivity;
import com.example.dpaper_shop.R;

public class FeedbackActivity extends AppCompatActivity {

    Button exit_feedbackBTN;
    ImageView open_flamp;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        exit_feedbackBTN = (Button) findViewById(R.id.exit_feedback);
        exit_feedbackBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FeedbackActivity.this, HomeActivity.class);
                startActivity(i);
            }
        });
        open_flamp = (ImageView) findViewById(R.id.open_flamp);
        open_flamp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://barnaul.flamp.ru/firm/dpaper_ru_magazin_dizajjnerskojj_bumagi_poligraficheskogo_oborudovaniya_i_raskhodnykh_materialov-70000001025725220"));
                startActivity(intent);
            }
        });
    }
}