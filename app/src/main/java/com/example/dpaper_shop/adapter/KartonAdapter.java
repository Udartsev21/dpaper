package com.example.dpaper_shop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.dpaper_shop.Model.Karton;
import com.example.dpaper_shop.R;

import java.util.List;

public class KartonAdapter extends RecyclerView.Adapter<KartonAdapter.KartonViewHolder> {

    Context context;

    List<Karton> karton_list;

    public KartonAdapter(Context context, List<Karton> karton_list) {

        this.context = context;
        this.karton_list = karton_list;
    }




    @NonNull
    @Override
    public KartonAdapter.KartonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View kartonItems = LayoutInflater.from(context).inflate(R.layout.karton_item, parent, false);
        return new KartonAdapter.KartonViewHolder(kartonItems);


    }

    @Override
    public void onBindViewHolder(@NonNull KartonAdapter.KartonViewHolder holder, int position) {

        int imageID = context.getResources().getIdentifier( karton_list.get(position).getImg(), "drawable", context.getPackageName());

        holder.product_image.setImageResource(imageID);
        holder.product_name.setText(karton_list.get(position).getKname());
        holder.product_description.setText(karton_list.get(position).getDesc());
        holder.product_price.setText(karton_list.get(position).getPrice());


    }

    @Override
    public int getItemCount() {
        return karton_list.size();
    }




    public static final class KartonViewHolder extends RecyclerView.ViewHolder {

        ImageView product_image;
        TextView product_name, product_description, product_price;



        public KartonViewHolder(@NonNull View itemView) {
            super(itemView);



            product_image = itemView.findViewById(R.id.product_image_karton);
            product_name = itemView.findViewById(R.id.product_name_karton);
            product_description = itemView.findViewById(R.id.product_description_karton);
            product_price = itemView.findViewById(R.id.product_price_karton);



        }
    }
}
