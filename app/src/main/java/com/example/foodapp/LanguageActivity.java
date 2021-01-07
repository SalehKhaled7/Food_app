package com.example.foodapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Locale;

public class LanguageActivity extends AppCompatActivity {
    ListView languageList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale();
        setContentView(R.layout.activity_language);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(getResources().getString(R.string.app_name));
        languageList = findViewById(R.id.languageList);

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Türkçe");
        arrayList.add("English");

        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,arrayList);
        languageList.setAdapter(arrayAdapter);
        languageList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if( position == 0){
                    setLocale("tr");
                    recreate();
                }
                else if( position == 1){
                    setLocale("en-rUs");
                    recreate();
                }
            }
        });

    }

    private void setLocale(String language) {
        Locale locale = new Locale(language);
        locale.setDefault(locale);
        Configuration configuration = new Configuration();
        configuration.locale = locale;
        getBaseContext().getResources().updateConfiguration(configuration,getBaseContext().getResources().getDisplayMetrics());

        SharedPreferences.Editor editor = getSharedPreferences("Setting",MODE_PRIVATE).edit();
        editor.putString("My_Lang",language);
        editor.apply();
    }

    public void loadLocale(){
        SharedPreferences sharedPreferences = getSharedPreferences("Setting", Activity.MODE_PRIVATE);
        String language = sharedPreferences.getString("My_Lang","");
        setLocale(language);
    }
}