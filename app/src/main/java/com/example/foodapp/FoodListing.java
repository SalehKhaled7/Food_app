package com.example.foodapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.Objects;

public class FoodListing extends AppCompatActivity {

    RecyclerView mRecyclerView;
    FoodListingAdapter foodListingAdapter;
    ArrayList<FoodDonationHelperClass> donationList;
    ArrayList<String> imageUriList;
    DatabaseReference reference;
    StorageReference sReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_food_listing);
        //recyclerview
        mRecyclerView = findViewById(R.id.recycler_view);
        //set its properties
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this)); //LinearLayout
        //mRecyclerView.setLayoutManager(new GridLayoutManager(this,2)); //GridLayout

        //list for donations from firebase


        reference = FirebaseDatabase.getInstance().getReference().child("donations");


        //sReference = FirebaseStorage.getInstance().getReference();
        //adapter
        getDataFromFirebase();
        foodListingAdapter = new FoodListingAdapter(getApplicationContext(), donationList);
        foodListingAdapter.notifyDataSetChanged();
        mRecyclerView.setAdapter(foodListingAdapter);

    }


    private void getDataFromFirebase() {
       // Query query = reference.child("donations");
        donationList = new ArrayList<>();
        imageUriList = new ArrayList<>();
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                FoodDonationHelperClass donation = new FoodDonationHelperClass();
                donation.setId(Objects.requireNonNull(snapshot.child("id").getValue()).toString());
                donation.setUserID(Objects.requireNonNull(snapshot.child("userId").getValue()).toString());
                donation.setTitle(Objects.requireNonNull(snapshot.child("title").getValue()).toString());
                donation.setDescription(Objects.requireNonNull(snapshot.child("description").getValue()).toString());
                donation.setAmount(Objects.requireNonNull(snapshot.child("amount").getValue()).toString());
                donation.setCreatedAt(Objects.requireNonNull(snapshot.child("createdAt").getValue()).toString());
                //get address
                AddresshelperClass address = new AddresshelperClass();
                address.setCity(Objects.requireNonNull(snapshot.child("address").child("city").getValue()).toString());
                address.setDistrict(Objects.requireNonNull(snapshot.child("address").child("district").getValue()).toString());
                address.setHomeDetails(Objects.requireNonNull(snapshot.child("address").child("homeDetails").getValue()).toString());
                donation.setAddress(address);
                //get images
                ArrayList<String> images = new ArrayList<>();
                for (int i = 0; i < snapshot.child("images").getChildrenCount(); i++) {
                    images.add(Objects.requireNonNull(snapshot.child("images").child(String.valueOf(i)).getValue()).toString());
                }

                donation.setImageList(images);

                //add donation object from firebase to donations list
                donationList.add(donation);



            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }


    //add models yo arraylist
//    private ArrayList<FoodDonationHelperClass> getPlayers(){
//        ArrayList<FoodDonationHelperClass> Donations = new ArrayList<>();
//
//        FoodDonationHelperClass p = new FoodDonationHelperClass();
//        p.setTitle("Kenny");
//        p.setDescription("Lorem Ipsum Kenny.Lorem Ipsum Kenny.Lorem Ipsum Kenny.Lorem Ipsum Kenny.Lorem Ipsum Kenny.Lorem Ipsum Kenny.Lorem Ipsum Kenny.Lorem Ipsum Kenny.Lorem Ipsum Kenny.Lorem Ipsum Kenny.");
//        Uri uri = Uri.parse("android.resource://com.example.foodapp/drawable/londonbridge");
//        ImageItem image = new ImageItem(uri);
//        imageUriList = new ArrayList<>();
//        imageUriList.add(uri.toString());
//        p.setImageList(imageUriList);
//        Donations.add(p);
//
//        p = new FoodDonationHelperClass();
//        p.setTitle("LondonBridge");
//        p.setDescription("Lorem Ipsum London Bridge.Lorem Ipsum .Lorem Ipsum .Lorem Ipsum .Lorem Ipsum .Lorem Ipsum .Lorem Ipsum .Lorem Ipsum .Lorem Ipsum .Lorem Ipsum .Lorem Ipsum .Lorem Ipsum .Lorem Ipsum .Lorem Ipsum .");
//        uri = Uri.parse("android.resource://com.example.foodapp/drawable/londonbridge");
//        image = new ImageItem(uri);
//        imageUriList = new ArrayList<>();
//        imageUriList.add(uri.toString());
//        p.setImageList(imageUriList);
//        Donations.add(p);
//
//        p = new FoodDonationHelperClass();
//        p.setTitle("Collesseum");
//        p.setDescription("Lorem Ipsum Collesseum.Lorem Ipsum .Lorem Ipsum .Lorem Ipsum .Lorem Ipsum .Lorem Ipsum .Lorem Ipsum .Lorem Ipsum .Lorem Ipsum .Lorem Ipsum .Lorem Ipsum .Lorem Ipsum .Lorem Ipsum .Lorem Ipsum .");
//        uri = Uri.parse("android.resource://com.example.foodapp/drawable/londonbridge");
//        image = new ImageItem(uri);
//        imageUriList = new ArrayList<>();
//        imageUriList.add(uri.toString());
//        p.setImageList(imageUriList);
//        Donations.add(p);
//
//        p = new FoodDonationHelperClass();
//        p.setTitle("Dunya");
//        p.setDescription("Lorem Ipsum Dunya.Lorem Ipsum .Lorem Ipsum .Lorem Ipsum .Lorem Ipsum .Lorem Ipsum .Lorem Ipsum .Lorem Ipsum .Lorem Ipsum .Lorem Ipsum .Lorem Ipsum .Lorem Ipsum .Lorem Ipsum .Lorem Ipsum .");
//        uri = Uri.parse("android.resource://com.example.foodapp/drawable/londonbridge");
//        image = new ImageItem(uri);
//        imageUriList = new ArrayList<>();
//        imageUriList.add(uri.toString());
//        p.setImageList(imageUriList);
//        Donations.add(p);
//
//        p = new FoodDonationHelperClass();
//        p.setTitle("Eiffel");
//        p.setDescription("Lorem Ipsum Eiffel.Lorem Ipsum .Lorem Ipsum .Lorem Ipsum .Lorem Ipsum .Lorem Ipsum .Lorem Ipsum .Lorem Ipsum .Lorem Ipsum .Lorem Ipsum .Lorem Ipsum .Lorem Ipsum .Lorem Ipsum .Lorem Ipsum .");
//        uri = Uri.parse("android.resource://com.example.foodapp/drawable/londonbridge");
//        image = new ImageItem(uri);
//        imageUriList = new ArrayList<>();
//        imageUriList.add(uri.toString());
//        p.setImageList(imageUriList);
//        Donations.add(p);
//
//        p = new FoodDonationHelperClass();
//        p.setTitle("Pisa");
//        p.setDescription("Lorem Ipsum Pisa.Lorem Ipsum .Lorem Ipsum .Lorem Ipsum .Lorem Ipsum .Lorem Ipsum .Lorem Ipsum .Lorem Ipsum .Lorem Ipsum .Lorem Ipsum .Lorem Ipsum .Lorem Ipsum .Lorem Ipsum .Lorem Ipsum .");
//        uri = Uri.parse("android.resource://com.example.foodapp/drawable/londonbridge");
//        image = new ImageItem(uri);
//        imageUriList = new ArrayList<>();
//        imageUriList.add(uri.toString());
//        p.setImageList(imageUriList);
//        Donations.add(p);
//
//        return  Donations;
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //called when you click search
                foodListingAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //called whenever you type each letter in searchview
                foodListingAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
            return true;

        }
        return super.onOptionsItemSelected(item);
    }


}