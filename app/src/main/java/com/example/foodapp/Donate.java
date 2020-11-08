package com.example.foodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Donate extends AppCompatActivity {

    TextInputLayout donationTitle,donationDescription,donationAmount;
    Button DonationList;

    FirebaseDatabase rootNode;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_donate);
        donationTitle=findViewById(R.id.donation_title);
        donationDescription=findViewById(R.id.donation_description);
        donationAmount=findViewById(R.id.donation_amount);

        DonationList=findViewById(R.id.donation_list_btn);
    }
    public void list(View view){//save the data to fire base

        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("foodDonations");//user is the table that we want to add the data to

        //get all the values
        String id ="1";
        String userID ="get current user id";
        String title = donationTitle.getEditText().getText().toString();
        String description = donationDescription.getEditText().getText().toString();
        String address="default address";
        String createdAt ="default date";
        String amount = donationAmount.getEditText().getText().toString();
        // String id = String.valueOf(phoneNumber);


        FoodDonationHelperClass foodDonationHelperClass =new FoodDonationHelperClass(id,userID,title,description,address,createdAt,amount);
        reference.child(id).setValue(foodDonationHelperClass); // phone number is PK
        Intent intent = new Intent(this,Home.class);
        startActivity(intent);
    }
}