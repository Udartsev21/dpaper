package com.example.dpaper_shop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dpaper_shop.Model.Kalka;
import com.example.dpaper_shop.Model.Tishu;
import com.example.dpaper_shop.R;

import java.util.List;

public class TishuAdapter extends RecyclerView.Adapter<TishuAdapter.TishuViewHolder> {

    Context context;

    List<Tishu> tishu_list;

    public TishuAdapter(Context context, List<Tishu> tishu_list) {

        this.context = context;
        this.tishu_list = tishu_list;
    }

    @NonNull
    @Override
    public TishuAdapter.TishuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View tishuItems = LayoutInflater.from(context).inflate(R.layout.tishu_item, parent, false);
        return new TishuAdapter.TishuViewHolder(tishuItems);

    }

    @Override
    public void onBindViewHolder(@NonNull TishuAdapter.TishuViewHolder holder, int position) {

        int imageID = context.getResources().getIdentifier( tishu_list.get(position).getImg(), "drawable", context.getPackageName());

        holder.product_image.setImageResource(imageID);
        holder.product_name.setText(tishu_list.get(position).getKname());
        holder.product_description.setText(tishu_list.get(position).getDesc());
        holder.product_price.setText(tishu_list.get(position).getPrice());


    }


    public int getItemCount() {
        return tishu_list.size();
    }

    public static final class TishuViewHolder extends RecyclerView.ViewHolder {

        ImageView product_image;
        TextView product_name, product_description, product_price;



        public TishuViewHolder(@NonNull View itemView) {

            super(itemView);

            product_image = itemView.findViewById(R.id.product_image_tishu);
            product_name = itemView.findViewById(R.id.product_name_tishu);
            product_description = itemView.findViewById(R.id.product_description_tishu);
            product_price = itemView.findViewById(R.id.product_price_tishu);

        }

    }


}
