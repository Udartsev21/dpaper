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

import com.example.dpaper_shop.CollectionPage;
import com.example.dpaper_shop.DesingPaperActivity;
import com.example.dpaper_shop.Model.Collections;
import com.example.dpaper_shop.Model.Collections_Page;
import com.example.dpaper_shop.Preview2Activity;
import com.example.dpaper_shop.Preview3Activity;
import com.example.dpaper_shop.PreviewActivity;
import com.example.dpaper_shop.R;

import java.util.List;

public class CollectionsAdapter extends RecyclerView.Adapter<CollectionsAdapter.CollectionsViewHolder> {


    Context context;
    List<Collections> collections;

    public CollectionsAdapter(Context context, List<Collections> collections) {
        this.context = context;
        this.collections = collections;
    }

    @NonNull
    @Override
    public CollectionsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View collectionItems = LayoutInflater.from(context).inflate(R.layout.collection_item, parent, false);
        return new CollectionsAdapter.CollectionsViewHolder(collectionItems);
    }

    @Override
    public void onBindViewHolder(@NonNull CollectionsViewHolder holder, @SuppressLint("RecyclerView") int position) {

         holder.collectBg.setBackgroundColor(Color.parseColor(collections.get(position).getColor()));

        int imageID = context.getResources().getIdentifier( collections.get(position).getImg(), "drawable", context.getPackageName());
        holder.collectImg.setImageResource(imageID);

        holder.collectTitle.setText(collections.get(position).getTittle());
        holder.manifacture.setText(collections.get(position).getManifacture());
        holder.country.setText(collections.get(position).getCountry());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Intent intent = new Intent(context, CollectionPage.class);

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((Activity) context, new Pair<View, String>(holder.collectImg, "collectionImage"));

                intent.putExtra("collectionBG",Color.parseColor(collections.get(position).getColor()));
                intent.putExtra("collectImg",imageID);
                intent.putExtra("collectId",collections.get(position).getId());
                intent.putExtra("manifacture",collections.get(position).getManifacture());
                intent.putExtra("country",collections.get(position).getCountry());

                context.startActivity(intent, options.toBundle());
            }
        });

    }



    @Override
    public int getItemCount() {
        return collections.size();
    }




    public static final class CollectionsViewHolder extends RecyclerView.ViewHolder{

LinearLayout collectBg;
ImageView collectImg;
TextView collectTitle, manifacture, country;


        public CollectionsViewHolder(@NonNull View itemView) {
            super(itemView);



            collectBg = itemView.findViewById(R.id.collectionBg);
            collectImg = itemView.findViewById(R.id.collectionImage);
            collectTitle = itemView.findViewById(R.id.collectionTitle);
            manifacture = itemView.findViewById(R.id.manifacture);
            country = itemView.findViewById(R.id.country);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {



                    int positionIndex = getAbsoluteAdapterPosition();
                    Toast.makeText(v.getContext(), "Вот он номер   " + positionIndex + " ", Toast.LENGTH_SHORT).show();

                    if(positionIndex == 0){
                        Intent intent = new Intent(v.getContext(), CollectionPage.class);
                        v.getContext().startActivity(intent);
                    }
                    if(positionIndex == 1){
                        Intent intent = new Intent(v.getContext(), Preview3Activity.class);
                        v.getContext().startActivity(intent);
                    }


                }
            });

        }
    }
}
