package com.example.dpaper_shop;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class FavoriteActivity extends AppCompatActivity {

    ImageView list;
    Button exit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) ListView order_list = findViewById(R.id.order_list);

        order_list.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, com.example.dpaper_shop.Model.Init.items_id.toArray()));

        list = (ImageView) findViewById(R.id.list_iv);
        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(FavoriteActivity.this, "Чуть позже добавлю", Toast.LENGTH_LONG).show();
            }
        });
        exit = (Button) findViewById(R.id.exit_favorite);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FavoriteActivity.this, HomeActivity.class);
                startActivity(i);
            }
        });
    }
}