package com.example.foodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {
    private static  int SPLASH_TIMER=5000;
    ImageView hand , heart;

    Animation handAnim ,heartAnim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        getSupportActionBar().hide();// hide the action bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN); //hide the status bar

        hand=findViewById(R.id.img_hand);
        heart=findViewById(R.id.img_heart);


        handAnim = AnimationUtils.loadAnimation(this,R.anim.hand_anim);
        heartAnim=AnimationUtils.loadAnimation(this,R.anim.heart_anim);

        //set animation on elements
        hand.setAnimation(handAnim);
        heart.setAnimation(heartAnim);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_TIMER);
    }
}