package com.example.dpaper_shop;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.dpaper_shop.Model.Products;
import com.example.dpaper_shop.Model.Sort;
import com.example.dpaper_shop.ViewHolder.ProductViewHolder;
import com.example.dpaper_shop.adapter.CollectionsAdapter;
import com.example.dpaper_shop.adapter.Sort_Adapter;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import io.paperdb.Paper;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;

    private CardView collect;
    private Button openBTN, cartBTN, OpenFullProductBTN;
    private  FloatingActionButton fabBTN, kategoryBTN;
    DatabaseReference productsRef;
    private RecyclerView recyclerView;

    private  RecyclerView recyclerViewAdr;
    Sort_Adapter sort_adapter;

    private ImageView image;
    RecyclerView.LayoutManager layoutManager;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        List<Sort> sortList = new ArrayList<>();
        Sort srt1 = new Sort("Элитная бумага","#09791c", "premire_paper");
        sortList.add(srt1);
        Sort srt2 = new Sort("Картон","#cb9a26", "karton");
        sortList.add(srt2);
        Sort srt3 = new Sort("Калька","#8e887c", "kalka");
        sortList.add(srt3);
        Sort srt4 = new Sort("Тишью","#a500d2","tishy");
        sortList.add(srt4);
        Sort srt5 = new Sort("Sale","#D2122E","hot_sale");
        sortList.add(srt5);
        Sort srt6 = new Sort("Feedback","#0039a6","feedback");
        sortList.add(srt6);
    setSortRecycler(sortList);

        productsRef = FirebaseDatabase.getInstance().getReference().child("Products");//

        collect = (CardView) findViewById(R.id.collectIMG);
        openBTN = (Button) findViewById(R.id.open);
        kategoryBTN = (FloatingActionButton) findViewById(R.id.kategory);
//        cartBTN = (Button) findViewById(R.id.buttonFavorite);
//        fabBTN = (FloatingActionButton)findViewById(R.id.fab);


kategoryBTN.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        Intent intIntent = new Intent(HomeActivity.this, KategoryActivity.class);
        startActivity(intIntent);
    }
});


//
//
//        collect.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                    collect.setVisibility(View.INVISIBLE);
//                    return;
//
//
//            }
//        });
//




        toolbar = (androidx.appcompat.widget.Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(":Dpaper");
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout)findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                drawer,
                toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close){


            public void onDrawerSlide(View drawerView, float slideOffset){

                super.onDrawerSlide(drawerView,slideOffset);
            }
        };


        drawer.addDrawerListener(toggle);
        toggle.syncState();



        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        recyclerView = findViewById(R.id.RecyclerMenu);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


    }

    private void setSortRecycler(List<Sort> sortList) {

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        recyclerViewAdr = findViewById(R.id.RecyclerSort);
        recyclerViewAdr.setLayoutManager(layoutManager);

        sort_adapter = new Sort_Adapter(this, sortList);
        recyclerViewAdr.setAdapter(sort_adapter);
    }


    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<Products>options = new FirebaseRecyclerOptions.Builder<Products>()
                .setQuery(productsRef, Products.class).build();

        FirebaseRecyclerAdapter<Products, ProductViewHolder> adapter = new FirebaseRecyclerAdapter<Products, ProductViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ProductViewHolder holder, int position, @NonNull Products model) {

                holder.txtProductName.setText(model.getPname());
                holder.txtProductPrice.setText( model.getPrice() + " ₽");
                holder.txtProductDescription.setText(model.getDescription());
                holder.openBTN.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_VIEW);
                        intent.addCategory(Intent.CATEGORY_BROWSABLE);
                        intent.setData(Uri.parse("https://dpaper.ru/"));
                        startActivity(intent);
                    }
                });
                Picasso.get().load(model.getImage()).into(holder.imageView);



            }

            @NonNull
            @Override
            public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_items_layout, parent, false);
                ProductViewHolder holder = new ProductViewHolder(view);

                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.sort_item, parent, false);
                ProductViewHolder holder1 = new ProductViewHolder(v);

                return holder;
            }
        };

        recyclerView.setAdapter(adapter);
        adapter.startListening();



    }

    @Override
   public void onBackPressed(){
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);

            if(drawerLayout.isDrawerOpen(GravityCompat.START)){
        drawerLayout.closeDrawer(GravityCompat.START);

            }
                else{
                super.onBackPressed();
        }

   }

    public void setSupportActionBar(androidx.appcompat.widget.Toolbar toolbar) {
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {

        return onSupportNavigateUp();

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        if(id == R.id.nav_korzina){
            Intent favoriteIntent = new Intent(HomeActivity.this, FavoriteActivity.class);
            startActivity(favoriteIntent);

        } else if(id == R.id.nav_order){
            Intent contactIntent = new Intent(HomeActivity.this, ContactActivity.class);
            startActivity(contactIntent);

        } else if(id == R.id.nav_categories){

            Intent categoryIntent = new Intent(HomeActivity.this, KategoryActivity.class);
            startActivity(categoryIntent);

        } else if(id == R.id.nav_settings){

            Intent loginIntent = new Intent(HomeActivity.this, SettingsActivity.class);
            startActivity(loginIntent);

        } else if(id == R.id.nav_exit){
            Paper.book().destroy();
            Intent loginIntent = new Intent(HomeActivity.this, LoginActivity.class);
            startActivity(loginIntent);
        }
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        drawerLayout.closeDrawer(GravityCompat.START);
        return false;
    }
}