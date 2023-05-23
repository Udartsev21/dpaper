package com.example.dpaper_shop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;



public class KategoryActivity extends AppCompatActivity {

    private Button DesingPaper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kategory);

        DesingPaper = (Button)findViewById(R.id.desing_paper);

        DesingPaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent homeIntent = new Intent(KategoryActivity.this,DesingPaperActivity.class);
                startActivity(homeIntent);
            }
        });
    }
}