package com.example.foodapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.foodapp.models.FoodDonationHelperClass;
import com.squareup.picasso.Picasso;

public class FoodDetail extends AppCompatActivity {
    TextView mTitleTv,mDescTv;
    ImageView mImageTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);

        //actionbar
        ActionBar actionBar = getSupportActionBar();

        mTitleTv = findViewById(R.id.dTitleTv);
        mDescTv = findViewById(R.id.dDescrTv);
        mImageTv = findViewById(R.id.dImageIv);

        //intent
        Bundle data = getIntent().getExtras();
        FoodDonationHelperClass donation = data.getParcelable("donation");
        String mImage = donation.getImageList().get(0);
        String mDescr = donation.getDescription();
        String mTitle = donation.getTitle();
        //set image
        Picasso.get().load(mImage).into(mImageTv);

        //set title to actionbar
        actionBar.setTitle(mTitle);

        //set data to our views
        mTitleTv.setText(mTitle);
       // mImageTv.setImageBitmap(bitmap);
        mDescTv.setText(mDescr);
    }







}