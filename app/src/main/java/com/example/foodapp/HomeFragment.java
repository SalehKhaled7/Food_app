package com.example.foodapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;

import com.example.foodapp.adapters.Main_slider_adapter;
import com.example.foodapp.models.Main_slider;

import java.util.ArrayList;


public class HomeFragment extends Fragment {
    Button donateBtn;
    ViewPager viewPager ;
    ArrayList<Main_slider> main_sliderArrayList;
    Main_slider_adapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_home, container, false);


        donateBtn = v.findViewById(R.id.donate_btn);
        viewPager=v.findViewById(R.id.viewPager);
        loadCards();
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });



        donateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),Donate.class));
            }
        });
        return v;
    }

    private void loadCards() {
        main_sliderArrayList = new ArrayList<>();

        main_sliderArrayList.add(new Main_slider(
                "this is title 1",
                " this is some random test instead of the description ",
                R.drawable.slider1
        ));
        main_sliderArrayList.add(new Main_slider(
                "this is title 2",
                " this is some random test instead of the description ",
                R.drawable.sider_2
        ));
        main_sliderArrayList.add(new Main_slider(
                "this is title 3",
                " this is some random test instead of the description ",
                R.drawable.slider_3
        ));
        main_sliderArrayList.add(new Main_slider(
                "this is title 4",
                " this is some random test instead of the description ",
                R.drawable.slider_4
        ));

        adapter= new Main_slider_adapter(getActivity(),main_sliderArrayList);
        viewPager.setAdapter(adapter);
        viewPager.setPadding(100,0,100,400);
    }
}