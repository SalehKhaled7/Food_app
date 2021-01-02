package com.example.foodapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

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
        Intent intent = getIntent();
        String mTitle = intent.getStringExtra("iTitle");
        String mDescr = intent.getStringExtra("iDescr");
        //byte[] mBytes = getIntent().getByteArrayExtra("iImage");
        //decode bytes array to bitmap
       // Bitmap bitmap = BitmapFactory.decodeByteArray(mBytes,0,mBytes.length);

        //set title to actionbar
        actionBar.setTitle(mTitle);

        //set data to our views
        mTitleTv.setText(mTitle);
       // mImageTv.setImageBitmap(bitmap);
        mDescTv.setText(mDescr);
    }







}