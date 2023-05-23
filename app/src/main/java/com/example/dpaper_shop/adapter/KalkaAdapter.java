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
import com.example.dpaper_shop.R;


import java.util.List;

public class KalkaAdapter extends RecyclerView.Adapter<KalkaAdapter.KalkaViewHolder>{

    Context context;

    List<Kalka> kalka_list;

    public KalkaAdapter(Context context, List<Kalka> kalka_list) {

        this.context = context;
        this.kalka_list = kalka_list;
    }


    @NonNull
    @Override
    public KalkaAdapter.KalkaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View kalkaItems = LayoutInflater.from(context).inflate(R.layout.kalka_item, parent, false);
        return new KalkaAdapter.KalkaViewHolder(kalkaItems);

    }

    @Override
    public void onBindViewHolder(@NonNull KalkaAdapter.KalkaViewHolder holder, int position) {

        int imageID = context.getResources().getIdentifier( kalka_list.get(position).getImg(), "drawable", context.getPackageName());

        holder.product_image.setImageResource(imageID);
        holder.product_name.setText(kalka_list.get(position).getKname());
        holder.product_description.setText(kalka_list.get(position).getDesc());
        holder.product_price.setText(kalka_list.get(position).getPrice());


    }

    public int getItemCount() {
        return kalka_list.size();
    }


    public static final class KalkaViewHolder extends RecyclerView.ViewHolder {

        ImageView product_image;
        TextView product_name, product_description, product_price;



        public KalkaViewHolder(@NonNull View itemView) {

            super(itemView);

            product_image = itemView.findViewById(R.id.product_image_kalka);
            product_name = itemView.findViewById(R.id.product_name_kalka);
            product_description = itemView.findViewById(R.id.product_description_kalka);
            product_price = itemView.findViewById(R.id.product_price_kalka);

        }

    }
}
