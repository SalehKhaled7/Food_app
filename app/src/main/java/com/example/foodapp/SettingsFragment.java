package com.example.foodapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class SettingsFragment extends Fragment {

    ListView settingsList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_settings, container, false);
        settingsList = v.findViewById(R.id.settingsList);

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(getResources().getString(R.string.profileLanguage));

        ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1,arrayList);
        settingsList.setAdapter(arrayAdapter);
        settingsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){
                    Intent intent = new Intent(getContext(),LanguageActivity.class);
                    startActivity(intent);

                }
            }
        });


        return v;
    }
}