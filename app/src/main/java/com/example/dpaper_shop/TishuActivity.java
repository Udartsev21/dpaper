package com.example.dpaper_shop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.dpaper_shop.Model.Tishu;
import com.example.dpaper_shop.adapter.TishuAdapter;

import java.util.ArrayList;
import java.util.List;

public class TishuActivity extends AppCompatActivity {

    RecyclerView tishuRecycler;
    TishuAdapter tishuAdapter;
    Button exitBTN;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tishu);


        List<Tishu> tishu_list = new ArrayList<>();

        tishu_list.add(new Tishu("1", "Тишью CARTOTECNICA ROSSI - F006 - SHOCKING PINK ярко-розовый 21 г/м² (500x760 мм), 24 листа", "Просмотров: 367", "936" + "₽", "tishu1"));
        tishu_list.add(new Tishu("1", "Тишью CARTOTECNICA ROSSI - F021 - PERVINCA AZURE лазурный 21 г/м² (500x760 мм), 24 листа", "Просмотров: 309", "425" + "₽", "tishu2"));
        tishu_list.add(new Tishu("1", "Тишью CARTOTECNICA ROSSI - Z804 - BRITAN RED красный 25 г/м² (500x760 мм), 24 листа", "Просмотров: 234", "122" + "₽", "tishu3"));
        tishu_list.add(new Tishu("1", "Тишью CARTOTECNICA ROSSI - F042 - MUSK GREEN белый 21 г/м² (500x760 мм), 24 листа", "Просмотров: 643", "206" + "₽", "tishu4"));
        tishu_list.add(new Tishu("1", "Тишью CARTOTECNICA ROSSI - Z802 - BRONZE STAR бронзовая звезда 25 г/м² (500x760 мм), 24 листа", "Просмотров: 531", "376" + "₽", "tishu5"));
        tishu_list.add(new Tishu("1", "Тишью CARTOTECNICA ROSSI - Z804 - GRADIENT лазурный+синий+зеленый 25 г/м² (500x760 мм), 24 листа", "Просмотров: 763", "523" + "₽", "tishu6"));



        tishuRecycler(tishu_list);

        exitBTN = (Button) findViewById(R.id.exit_tishu);
        exitBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TishuActivity.this, HomeActivity.class);
                startActivity(i);

            }
        });

    }

    private void tishuRecycler(List<Tishu> tishu_list) {

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);

        tishuRecycler = findViewById(R.id.RecyclerTishu);
        tishuRecycler.setLayoutManager(layoutManager);

        tishuAdapter = new TishuAdapter(this, tishu_list);
        tishuRecycler.setAdapter(tishuAdapter);
    }


}