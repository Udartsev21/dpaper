package com.example.dpaper_shop;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Preview2Activity extends AppCompatActivity {

    private Button next2Btn;
    private ImageButton skipBtn;




    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview2);

        next2Btn =  (Button) findViewById(R.id.next2);
        skipBtn =  (ImageButton) findViewById(R.id.skipBtn);

        skipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent2 = new Intent(Preview2Activity.this, MainActivity.class);
                startActivity(intent2);
            }
        });


        next2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Preview2Activity.this, Preview4Activity.class);
                startActivity(intent1);
            }
        });
    }
}