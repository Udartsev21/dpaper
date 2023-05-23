package com.example.dpaper_shop.ViewHolder;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.dpaper_shop.Interface.ItemClickListener;
import com.example.dpaper_shop.R;


public class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{



    public TextView txtProductName, txtProductDescription, txtProductPrice;
    public ImageView imageView;
    public ItemClickListener listener;
public Button openBTN;

    public ProductViewHolder(View itemView)
    {
        super(itemView);

        imageView = itemView.findViewById(R.id.product_image);
        openBTN = itemView.findViewById(R.id.open);
        txtProductName = itemView.findViewById(R.id.product_name);
        txtProductDescription = itemView.findViewById(R.id.product_description);
        txtProductPrice= itemView.findViewById(R.id.product_price);


    }

    public void setItemClickListener(ItemClickListener listener) {this.listener = listener;}



    @Override
    public void onClick(View v) {
        listener.OnClick(v, getAbsoluteAdapterPosition(), false);
    }
}
