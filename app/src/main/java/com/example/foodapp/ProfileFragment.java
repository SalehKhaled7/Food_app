package com.example.foodapp;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import org.intellij.lang.annotations.Language;


public class ProfileFragment extends Fragment {
    CardView profile;
    CardView notice;
    CardView settings;
    CardView language;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_profile, container, false);
        profile =v.findViewById(R.id.profile);
        notice =v.findViewById(R.id.notice);
        settings =v.findViewById(R.id.settings);
        language =v.findViewById(R.id.language);

        profile.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivityForResult(new Intent(getActivity(),ProfileActivity.class), 0);
            }
        });
        notice.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivityForResult(new Intent(getActivity(),ProfileActivity.class), 0);
            }
        });
        profile.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivityForResult(new Intent(getActivity(),ProfileActivity.class), 0);
            }
        });
        language.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivityForResult(new Intent(getActivity(), LanguageActivity.class), 0);
            }
        });
        return v;
    }
}