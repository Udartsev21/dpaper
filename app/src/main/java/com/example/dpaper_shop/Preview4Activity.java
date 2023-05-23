package com.example.dpaper_shop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Preview4Activity extends AppCompatActivity {

    private Button next4Btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview4);

        next4Btn =  (Button) findViewById(R.id.next4);


        next4Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Preview4Activity.this, Preview3Activity.class);
                startActivity(intent1);
            }
        });

    }
}