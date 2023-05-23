package com.example.dpaper_shop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


public class PreviewActivity extends AppCompatActivity {


    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);



    }

    protected void onStart() {
        super.onStart();
        setContentView(R.layout.splash);

        imageView = findViewById(R.id.dpaper_logo_splash);
        imageView.setAlpha(0f);

        imageView.animate().alpha(1f).setDuration(1500);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
             Intent i = new Intent(PreviewActivity.this, Preview2Activity.class);
             startActivity(i);
             finish();
            }
        }, 5*1000);
    }
}