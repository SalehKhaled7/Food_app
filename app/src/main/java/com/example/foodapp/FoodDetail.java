package com.example.foodapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.foodapp.adapters.FoodDetailImagesAdapter;
import com.example.foodapp.models.FoodDonationHelperClass;
import com.squareup.picasso.Picasso;

public class FoodDetail extends AppCompatActivity {
    TextView mTitleTv,mDescTv,mAmountTv;
    ImageView mImageTv;
    RecyclerView img_recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();// hide the action bar

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);

        //actionbar
        ActionBar actionBar = getSupportActionBar();

        mTitleTv = findViewById(R.id.dTitleTv);
        mDescTv = findViewById(R.id.dDescrTv);
        mImageTv = findViewById(R.id.dImageIv);
        mAmountTv=findViewById(R.id.damountTv);
        img_recyclerView = findViewById(R.id.food_detail_img_recycler_view);


        //intent
        Bundle data = getIntent().getExtras();
        FoodDonationHelperClass donation = data.getParcelable("donation");
        FoodDetailImagesAdapter adapter = new FoodDetailImagesAdapter(getApplicationContext(),donation.getImageList());
        img_recyclerView.setAdapter(adapter);
        img_recyclerView.setLayoutManager(new LinearLayoutManager(this));
        String mImage = donation.getImageList().get(0);
        String mDescr = donation.getDescription();
        String mTitle = donation.getTitle();
        String mAmount = "for: "+donation.getAmount()+" people";
        //set image
        Picasso.get().load(mImage).into(mImageTv);

        //set title to actionbar
        actionBar.setTitle(mTitle);

        //set data to our views
        mTitleTv.setText(mTitle);
       // mImageTv.setImageBitmap(bitmap);
        mDescTv.setText(mDescr);
        mAmountTv.setText(mAmount);
    }







}