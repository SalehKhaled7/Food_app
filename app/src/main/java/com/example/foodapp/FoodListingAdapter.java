package com.example.foodapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class FoodListingAdapter extends RecyclerView.Adapter<MyHolder> implements Filterable {

    Context c;
    ArrayList<FoodDonationHelperClass> Donations,filterList;
    CustomFilter filter;

    public FoodListingAdapter(Context c, ArrayList<FoodDonationHelperClass> Donations) {
        this.c = c;
        this.Donations = Donations;
        this.filterList = Donations;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //convert xml to view obj
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.model,null);

        return new MyHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        //bind data to our views
        holder.mTitleTv.setText(Donations.get(position).getTitle());
        holder.mDescrTv.setText(Donations.get(position).getDescription());
        holder.mimageIv.setImageURI(Uri.parse(Donations.get(position).getImageList().get(0)));

        //animation
        Animation animation = AnimationUtils.loadAnimation(c, android.R.anim.slide_in_left);
        holder.itemView.startAnimation(animation);

        /*Use when you want to put each item data to same activity*/
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                //get data from item clicked
                String title = Donations.get(pos).getTitle();
                String descr = Donations.get(pos).getDescription();
                BitmapDrawable bitmapDrawable = (BitmapDrawable) holder.mimageIv.getDrawable();
                //get Bitmap from drawable
                Bitmap bitmap = bitmapDrawable.getBitmap();
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                //compress image
                bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
                //convert to bytes array
                byte[] bytes = stream.toByteArray();

                //intent,put data to intent,start activity
                Intent intent = new Intent(c, FoodDetail.class);
                intent.putExtra("iTitle",title);
                intent.putExtra("iDescr",descr);
                intent.putExtra("iImage",bytes);
                c.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return Donations.size();
    }

    @Override
    public Filter getFilter() {
        if (filter == null){
            filter = new CustomFilter(filterList,this);
        }
        return filter;
    }

}
