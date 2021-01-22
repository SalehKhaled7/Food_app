package com.example.foodapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.R;
import com.squareup.picasso.Picasso;

import java.net.URL;
import java.util.ArrayList;

public class FoodDetailImagesAdapter extends RecyclerView.Adapter<FoodDetailImagesAdapter.viewHolder> {

    ArrayList<String> imagesList;
    Context context;
    public FoodDetailImagesAdapter(Context c , ArrayList<String> images){
        context = c;
        imagesList=images;
    }
    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.food_detail_recycler_item,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        Picasso.get().load(imagesList.get(position)).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class viewHolder extends RecyclerView.ViewHolder {
        ImageView image ;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.dImageIv);
        }
    }
}
