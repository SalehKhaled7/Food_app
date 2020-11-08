package com.example.foodapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;


public class CategoryFragment extends Fragment {
    RelativeLayout foodList;
    RelativeLayout ClothingList;
    RelativeLayout OtherList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View v = inflater.inflate(R.layout.fragment_category, container, false);
        foodList =v.findViewById(R.id.foodList);
        ClothingList =v.findViewById(R.id.ClothingList);
        OtherList =v.findViewById(R.id.OtherList);
        foodList.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivityForResult(new Intent(getActivity(),FoodListing.class), 0);
            }
        });
        ClothingList.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivityForResult(new Intent(getActivity(),FoodListing.class), 0);
            }
        });
        OtherList.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivityForResult(new Intent(getActivity(),FoodListing.class), 0);
            }
        });
        return v;
    }

}