package com.example.foodapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodapp.adapters.My_donations_adapter;
import com.example.foodapp.models.FoodDonationHelperClass;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class My_donations_fragment extends Fragment {

    RecyclerView my_donations_recRecyclerView;
    ArrayList<FoodDonationHelperClass> my_donations_list;
    RecyclerView.LayoutManager layoutManager;
    My_donations_adapter adapter;
    FirebaseUser user;

    DatabaseReference reference;
    private FirebaseAuth mAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_my_donations, container, false);
        my_donations_recRecyclerView = v.findViewById(R.id.my_donations_rv);
        my_donations_list = new ArrayList<>();
        user = FirebaseAuth.getInstance().getCurrentUser();
        mAuth = FirebaseAuth.getInstance();

        getDataFromFireBase();
        adapter = new My_donations_adapter(my_donations_list);
        layoutManager = new LinearLayoutManager(getContext());
        my_donations_recRecyclerView.setLayoutManager(layoutManager);
        my_donations_recRecyclerView.setAdapter(adapter);
        my_donations_recRecyclerView.setHasFixedSize(true);
        return v;
    }

    private void getDataFromFireBase() {
        reference = FirebaseDatabase.getInstance().getReference().child("donations");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                my_donations_list.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    if (snapshot.child("userID").getValue().toString().equals(user.getUid())){
                        FoodDonationHelperClass donation = new FoodDonationHelperClass();
                        donation.setId(snapshot.child("id").getValue().toString());
                        my_donations_list.add(donation);
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}