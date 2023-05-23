package com.example.dpaper_shop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.dpaper_shop.Model.Karton;
import com.example.dpaper_shop.adapter.KartonAdapter;

import java.util.ArrayList;
import java.util.List;

public class KartonActivity extends AppCompatActivity {

    RecyclerView kartonRecycler;
    KartonAdapter kartonAdapter;
    Button exitBTN;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_karton);


        List<Karton> karton_list = new ArrayList<>();
        karton_list.add(new Karton("1", "Переплётный картон ESKABOARD - 630 г/м² (700x1000x1,0 мм)", "Просмотров: 2983", "94" + "₽", "item1"));
        karton_list.add(new Karton("2", "Переплётный картон СУРАЖ - 1900 г/м² (700x1000x3,0 мм)", "Просмотров: 778", "135" + "₽", "item2"));
        karton_list.add(new Karton("3", "Переплётный картон СУРАЖ - 1100 г/м² (700x1000x1,75 мм)", "Просмотров: 524", "184" + "₽", "item3"));
        karton_list.add(new Karton("4", "Переплётный картон ESKABOARD - 790 г/м² (700x1000x1,25 мм)", "Просмотров: 454", "119" + "₽", "item4"));
        karton_list.add(new Karton("5", "Переплётный картон ESKABOARD - 1105 г/м² (620x940x1,75 мм)", "Просмотров: 339", "170" + "₽", "item5"));
        karton_list.add(new Karton("6", "Пивной картон PANKADISK - белый 390 г/м² (700x1000x0,9 мм)", "Просмотров: 407", "108" + "₽", "item6"));

        kartonRecycler(karton_list);


        exitBTN = (Button) findViewById(R.id.exit_karton);
        exitBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(KartonActivity.this, HomeActivity.class);
                startActivity(i);

            }
        });



    }

    private void kartonRecycler(List<Karton> karton_list) {

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);

        kartonRecycler = findViewById(R.id.RecyclerKarton);
        kartonRecycler.setLayoutManager(layoutManager);

        kartonAdapter = new KartonAdapter(this, karton_list);
        kartonRecycler.setAdapter(kartonAdapter);
    }
}