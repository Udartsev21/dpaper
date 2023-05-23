package com.example.dpaper_shop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class CategoryActivity extends AppCompatActivity {

    private ImageView DesignerPaper, Tishu, Karton, Kalka;
    private Button exitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        init();

        DesignerPaper.setOnClickListener(view -> {
            Intent DesignerPaperIntent = new Intent(CategoryActivity.this, CreateProductActivity.class);
            DesignerPaperIntent.putExtra("category", "Дизайнерская бумага" );
            startActivity(DesignerPaperIntent);
        });

        Tishu.setOnClickListener(view -> {
            Intent tishuIntent = new Intent(CategoryActivity.this, CreateProductActivity.class);
            tishuIntent.putExtra("category", "Тишью" );
            startActivity(tishuIntent);
        });

        Karton.setOnClickListener(view -> {
            Intent kartonIntent = new Intent(CategoryActivity.this, CreateProductActivity.class);
            kartonIntent.putExtra("category", "Картон" );
            startActivity(kartonIntent);
        });

        Kalka.setOnClickListener(view -> {
            Intent kalkaIntent = new Intent(CategoryActivity.this, CreateProductActivity.class);
            kalkaIntent.putExtra("category", "Калька" );
            startActivity(kalkaIntent);
        });

        exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent exitIntent = new Intent(CategoryActivity.this,LoginActivity.class);
                startActivity(exitIntent);
            }
        });


    }

    private void init() {

        DesignerPaper = findViewById(R.id.card1);
        Tishu = findViewById(R.id.card2);
        Karton = findViewById(R.id.card3);
        Kalka = findViewById(R.id.card4);
        exitBtn = (Button) findViewById(R.id.exit);
    }


}