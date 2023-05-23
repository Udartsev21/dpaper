package com.example.dpaper_shop.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.dpaper_shop.CollectionsActivity;
import com.example.dpaper_shop.DesingPaperActivity;
import com.example.dpaper_shop.KalkaActivity;
import com.example.dpaper_shop.KartonActivity;
import com.example.dpaper_shop.Model.Sort;
import com.example.dpaper_shop.Preview2Activity;
import com.example.dpaper_shop.Preview3Activity;
import com.example.dpaper_shop.PreviewActivity;
import com.example.dpaper_shop.R;
import com.example.dpaper_shop.TishuActivity;
import com.example.dpaper_shop.nav_adv.FeedbackActivity;
import com.example.dpaper_shop.nav_adv.SaleActivity;

import java.util.List;

public class Sort_Adapter extends RecyclerView.Adapter<Sort_Adapter.SortViewHolder> {

 public Context context;
    List<Sort> sorting;


    public Sort_Adapter(Context context, List<Sort> sorting) {
        this.context = context;
        this.sorting = sorting;
    }


    @NonNull
    @Override
    public Sort_Adapter.SortViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View sortItems = LayoutInflater.from(context).inflate(R.layout.sort_item, parent, false);
        return new Sort_Adapter.SortViewHolder(sortItems);
    }
    @Override
    public void onBindViewHolder(@NonNull Sort_Adapter.SortViewHolder holder, @SuppressLint("RecyclerView") int position) {
        int imageID = context.getResources().getIdentifier( sorting.get(position).getSimg(), "drawable", context.getPackageName());
        holder.sort_image.setImageResource(imageID);
        holder.sortBg.setBackgroundColor(Color.parseColor(sorting.get(position).getColor()));
        holder.sort_name_tv.setText(sorting.get(position).getName());





//        holder.sort_name_tv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(context, "Нажал на текст", Toast.LENGTH_SHORT).show();
//            }
//        });
//        holder.sortBg.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(context, "Нажал на LinnerLayout", Toast.LENGTH_SHORT).show();
//            }
//        });

//
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent intent = new Intent(context, CollectionsActivity.class);
//
//
//                intent.putExtra("sortBg",Color.parseColor(sorting.get(position).getColor()));
//                intent.putExtra("sort_name_tv",sorting.get(position).getName());
//
//                context.startActivity(intent);
//            }
//        });

    }


    @Override
    public int getItemCount() {
        return sorting.size();
    }



    public static final class SortViewHolder extends RecyclerView.ViewHolder {

        LinearLayout sortBg;
        TextView sort_name_tv;
        ImageView sort_image;

        public SortViewHolder(@NonNull View itemView) {
            super(itemView);
            sortBg = itemView.findViewById(R.id.bg);
            sort_name_tv = itemView.findViewById(R.id.sort_name);
sort_image = itemView.findViewById(R.id.image_sort);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int positionIndex = getAbsoluteAdapterPosition();
                    if(positionIndex == 0){
                        Intent intent = new Intent(v.getContext(), DesingPaperActivity.class);
                        v.getContext().startActivity(intent);
                    }
                    if(positionIndex == 1){
                        Intent intent = new Intent(v.getContext(), KartonActivity.class);
                        v.getContext().startActivity(intent);
                    }
                    if(positionIndex == 2){

                        Intent intent = new Intent(v.getContext(), KalkaActivity.class);
                        v.getContext().startActivity(intent);
                    }
                    if(positionIndex == 3){

                        Intent intent = new Intent(v.getContext(), TishuActivity.class);
                        v.getContext().startActivity(intent);
                    }
                    if(positionIndex == 4){
                        Intent intent = new Intent(v.getContext(), SaleActivity.class);
                        v.getContext().startActivity(intent);
                    }
                    if(positionIndex == 5){
                        Intent intent = new Intent(v.getContext(), FeedbackActivity.class);
                        v.getContext().startActivity(intent);
                    }

                }
            });

        }


    }
}
