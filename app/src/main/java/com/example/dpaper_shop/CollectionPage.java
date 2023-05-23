package com.example.dpaper_shop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.ActivityOptions;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.dpaper_shop.Model.Collections;
import com.example.dpaper_shop.Model.Collections_Page;
import com.example.dpaper_shop.Model.Init;
import com.example.dpaper_shop.adapter.CollectionsAdapter;
import com.example.dpaper_shop.adapter.Collections_Page_Adapter;

import java.util.ArrayList;
import java.util.List;

public class CollectionPage extends AppCompatActivity {

    RecyclerView collection_pageRV;
    Collections_Page_Adapter collections_page_adapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection_page);
        ImageView collectImg = findViewById(R.id.collection_pageIV);

        List<Collections_Page> page_list = new ArrayList<>();
        page_list.add(new Collections_Page(1, "majestic_item", "Бумага MAJESTIC (CHAMELEON) - LIGHT BLUE горный хрусталь (голубой свет) 120 г/м² (720x1020 мм)",
                "Majestic", "145" + "₽", "#232323"));
        page_list.add(new Collections_Page(2, "majestic_item1", "Бумага MAJESTIC - CASINO GOLD золото казино 290 г/м² (720x1020 мм)",
                "Majestic", "390" + "₽", "#232323" ));
        page_list.add(new Collections_Page(3, "majestic_item2", "Бумага CRUSH - COFFEE кофейный 250 г/м² (720x1020 мм)",
                "Majestic", "326" + "₽", "#232323"));
        page_list.add(new Collections_Page(4, "majestic_item3", "Бумага MAJESTIC - ANTHRACITE антрацит 120 г/м² (720x1020 мм)",
                "Majestic", "220" + "₽", "#232323"));
        page_list.add(new Collections_Page(5, "majestic_item4", "Бумага COCTAIL - BLUE MOON тёмно-синий 120 г/м² (700x1000 мм)",
                "Majestic", "189" + "₽", "#232323"));





        collectImg.setImageResource(getIntent().getIntExtra("collectImg", 0));

        collection_pageRV(page_list);



    }





    private void collection_pageRV(List<Collections_Page> page_list) {

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);

        collection_pageRV = findViewById(R.id.collection_pageRV);
        collection_pageRV.setLayoutManager(layoutManager);
        collections_page_adapter = new Collections_Page_Adapter(this, page_list);
        collection_pageRV.setAdapter(collections_page_adapter);

    }
public void init_collect(View view){

    int item_id = getIntent().getIntExtra("collectId", 0);
    Init.items_id.add(item_id);
    Toast.makeText(this, "Инициализация прошла успешно", Toast.LENGTH_SHORT).show();
}

}