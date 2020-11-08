package com.example.foodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class FoodListing extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_food_listing);
    }
}