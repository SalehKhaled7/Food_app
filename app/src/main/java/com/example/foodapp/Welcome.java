package com.example.foodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

public class Welcome extends AppCompatActivity {
    TextView welcome_msg;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        welcome_msg = findViewById(R.id.welcome_msg);

        welcome_msg.setText("hello "+getIntent().getStringExtra("name"));
    }
}