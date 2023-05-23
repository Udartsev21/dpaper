package com.example.dpaper_shop.nav_adv;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.dpaper_shop.DesingPaperActivity;
import com.example.dpaper_shop.HomeActivity;
import com.example.dpaper_shop.R;

public class SaleActivity extends AppCompatActivity {

    ImageView coloplanIv, burano_Iv, danial_Iv;
    Button exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sale);



        coloplanIv = (ImageView)findViewById(R.id.colorplan);
        burano_Iv = (ImageView)findViewById(R.id.burano);
        danial_Iv = (ImageView)findViewById(R.id.danial);
exit = (Button) findViewById(R.id.exit_sale_button);

exit.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent i = new Intent(SaleActivity.this, HomeActivity.class);
        startActivity(i);
    }
});

        coloplanIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SaleActivity.this, DesingPaperActivity.class);
                startActivity(intent, Bundle.EMPTY);
            }
        });
        burano_Iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SaleActivity.this, DesingPaperActivity.class);
                startActivity(intent, Bundle.EMPTY);
            }
        });
        danial_Iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SaleActivity.this, DesingPaperActivity.class);
                startActivity(intent, Bundle.EMPTY);
            }
        });
   }
}

