package com.example.dpaper_shop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.dpaper_shop.Model.Collections;
import com.example.dpaper_shop.R;
import com.example.dpaper_shop.adapter.CollectionsAdapter;

import java.util.ArrayList;
import java.util.List;

public class DesingPaperActivity extends AppCompatActivity {

    RecyclerView collectionRecycler;
    CollectionsAdapter collectionsAdapter;

    Button favoriteBTN;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desing_paper);

        favoriteBTN = (Button) findViewById(R.id.add_to_favorite);
        favoriteBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DesingPaperActivity.this, FavoriteActivity.class);
                startActivity(i);
            }
        });

        List<Collections> collectionsList = new ArrayList<>();
        collectionsList.add(new Collections(1, "majestic_full", "Коллекция: Majestic","Италия","Favini", "#373737"));
        collectionsList.add(new Collections(2, "touch_cover", "Коллекция: Touche Cover","США","Neenah Paper", "#043200"));
        collectionsList.add(new Collections(3, "the_kiss", "Коллекция: ","Германия","Lakepaper", "#3D0037"));
        collectionsList.add(new Collections(4, "danial", "Коллекция: Dainel SG","Франция","Sef", "#2C0000"));
        collectionsList.add(new Collections(5, "burano", "Коллекция: Burano","Италия","Favini", "#5E4300"));
        collectionsList.add(new Collections(6, "colorplan", "Коллекция: Colorplan","Великобритания","GF Smith", "#5E0000"));


        setCollectionsRecycler(collectionsList );
    }

    private void setCollectionsRecycler(List<Collections> collectionsList) {

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);

        collectionRecycler = findViewById(R.id.collectionRecycler);
        collectionRecycler.setLayoutManager(layoutManager);
        collectionsAdapter = new CollectionsAdapter(this, collectionsList);
        collectionRecycler.setAdapter(collectionsAdapter);

    }
}