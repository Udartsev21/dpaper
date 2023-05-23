package com.example.dpaper_shop.adapter;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.dpaper_shop.CollectionPage;
import com.example.dpaper_shop.Model.Collections_Page;
import com.example.dpaper_shop.R;

import java.util.List;

public class Collections_Page_Adapter extends RecyclerView.Adapter<Collections_Page_Adapter.CollectionsPageViewHolder> {

    Context context;
    static List<Collections_Page> page_list;

    public Collections_Page_Adapter(Context context, List<Collections_Page> page_list) {
        this.context = context;
        this.page_list = page_list;
    }

    @NonNull
    @Override
    public Collections_Page_Adapter.CollectionsPageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View collectionPageItems = LayoutInflater.from(context).inflate(R.layout.collection_page_item, parent, false);
        return new Collections_Page_Adapter.CollectionsPageViewHolder(collectionPageItems);

    }

    @Override
    public void onBindViewHolder(@NonNull Collections_Page_Adapter.CollectionsPageViewHolder holder, int position) {


        int imageID = context.getResources().getIdentifier( page_list.get(position).getImg(), "drawable", context.getPackageName());

        holder.product_image.setImageResource(imageID);

        holder.product_name.setText(page_list.get(position).getPname());
        holder.product_description.setText(page_list.get(position).getDesc());
        holder.product_price.setText(page_list.get(position).getPrice());



    }

    @Override
    public int getItemCount() {
        return page_list.size();
    }

    public static final class CollectionsPageViewHolder extends RecyclerView.ViewHolder {

ImageView product_image;
ImageView collection_page_iv;
TextView product_name, product_description, product_price;
ListView listview;
LinearLayout collection_pageBG;

        public CollectionsPageViewHolder(@NonNull View itemView) {
            super(itemView);
            collection_page_iv = itemView.findViewById(R.id.collection_pageIV);
            collection_pageBG = itemView.findViewById(R.id.collectPageBg);
            product_image = itemView.findViewById(R.id.product_image);
            product_name = itemView.findViewById(R.id.product_name);
            product_description = itemView.findViewById(R.id.product_description);
            product_price = itemView.findViewById(R.id.product_price);
            listview = itemView.findViewById(R.id.order_list);


        }
    }
}
