package com.example.foodapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.foodapp.models.AddresshelperClass;
import com.example.foodapp.models.FoodDonationHelperClass;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
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

        //getSupportActionBar().hide();// hide the action bar
        ActionBar actionBar = getSupportActionBar();
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
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(foodListingAdapter);

    }


    private void getDataFromFirebase() {
       // Query query = reference.child("donations");
        donationList = new ArrayList<>();
        imageUriList = new ArrayList<>();
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                donationList.clear();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){

                    FoodDonationHelperClass donation = new FoodDonationHelperClass();
                    donation.setId(snapshot.child("id").getValue().toString());
                    donation.setUserID(snapshot.child("userID").getValue().toString());
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
                        images.add(snapshot.child("images").child(String.valueOf(i)).getValue().toString());
                    }

                    donation.setImageList(images);

                    //add donation object from firebase to donations list
                    donationList.add(donation);
                }
                foodListingAdapter.notifyDataSetChanged();


            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }



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