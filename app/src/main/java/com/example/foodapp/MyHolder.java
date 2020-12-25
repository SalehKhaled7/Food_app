package com.example.foodapp;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {
    //Views
    ImageView mimageIv;
    TextView mTitleTv,mDescrTv;
    ItemClickListener itemClickListener;

    MyHolder(@NonNull View itemView) {
        super(itemView);
        this.mimageIv = itemView.findViewById(R.id.modelImageIv);
        this.mTitleTv = itemView.findViewById(R.id.modelTitleTv);
        this.mDescrTv = itemView.findViewById(R.id.modelDescrTv);
        itemView.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        this.itemClickListener.onItemClick(v,getLayoutPosition());
    }
    public void setItemClickListener(ItemClickListener ic){
        this.itemClickListener = ic;
    }
}
