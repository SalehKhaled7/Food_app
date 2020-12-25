package com.example.foodapp;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DonationImageListAdapter extends RecyclerView.Adapter<DonationImageListAdapter.ViewHolder> {

    private final ArrayList<ImageItem> mImageItemList;

    public class ViewHolder extends RecyclerView.ViewHolder{//connect elements in item layout to recycler view

        public ImageView mImageView ;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.donation_img);
        }
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {//connect item layout with the recycler view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.donation_image_item,parent,false);
        ViewHolder evh = new ViewHolder(view);
        return evh;
    }

    public DonationImageListAdapter(ArrayList<ImageItem> mImageItemList) { // connect item array list to recycler view
        this.mImageItemList = mImageItemList;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ImageItem currentItem = mImageItemList.get(position);
        holder.mImageView.setImageURI(currentItem.imageUri);//

    }

    @Override
    public int getItemCount() {
        return mImageItemList.size();
    }



}
