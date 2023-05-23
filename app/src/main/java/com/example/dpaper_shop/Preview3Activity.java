package com.example.dpaper_shop;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class Preview3Activity extends AppCompatActivity {
    private Button next3Btn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview3);

        next3Btn = (Button) findViewById(R.id.next3);

        next3Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Preview3Activity.this, RegisterActivity.class);
                startActivity(intent1);
            }
        });
    }
}