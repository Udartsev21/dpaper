package com.example.dpaper_shop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.dpaper_shop.Model.Kalka;
import com.example.dpaper_shop.adapter.KalkaAdapter;

import java.util.ArrayList;
import java.util.List;

public class KalkaActivity extends AppCompatActivity {

    RecyclerView kalkaRecycler;
    KalkaAdapter kalkaAdapter;
    Button exitBTN;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kalka);

        List<Kalka> kalka_list = new ArrayList<>();

        kalka_list.add(new Kalka("1", "Калька ZANDERS SPECTRAL - SNOW-WHITE снежно-белый 200 г/м² (650x920 мм)", "Просмотров: 537", "245" + "₽", "kalka3"));
        kalka_list.add(new Kalka("2", "Калька ZANDERS SPECTRAL - SNOW-WHITE снежно-белый 100 г/м² (650x920 мм)", "Просмотров: 419", "112" + "₽", "kalka2"));
        kalka_list.add(new Kalka("3", "Калька CURIOUS TRANSLUCENTS - CLEAR прозрачная 180 г/м² (700x1000 мм)", "Просмотров: 752", "358" + "₽", "kalka1"));
        kalka_list.add(new Kalka("4", "Калька CURIOUS TRANSLUCENTS COLOR - SILVER серебро-перламутр 100 г/м² (700x1000 мм)", "Просмотров: 752", "358" + "₽", "kalka4"));
        kalka_list.add(new Kalka("5", "Калька CURIOUS TRANSLUCENTS COLOR - WHITE IRIDISCENT белый перламутр 100 г/м² (700x1000 мм)", "Просмотров: 418", "216" + "₽", "kalka5"));
        kalka_list.add(new Kalka("6", "Калька ZANDERS SPECTRAL - WHITE белый 100 г/м² (650x920 мм)", "Просмотров: 356", "112" + "₽", "kalka6"));

        kalkaRecycler(kalka_list);

        exitBTN = (Button) findViewById(R.id.exit_kalka);
        exitBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(KalkaActivity.this, HomeActivity.class);
                startActivity(i);

            }
        });

    }

    private void kalkaRecycler(List<Kalka> kalka_list) {

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);

        kalkaRecycler = findViewById(R.id.RecyclerKalka);
        kalkaRecycler.setLayoutManager(layoutManager);

        kalkaAdapter = new KalkaAdapter(this, kalka_list);
        kalkaRecycler.setAdapter(kalkaAdapter);
    }
}