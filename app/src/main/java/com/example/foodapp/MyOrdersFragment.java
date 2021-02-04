package com.example.foodapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodapp.adapters.My_orders_adapter;
import com.example.foodapp.models.AddresshelperClass;
import com.example.foodapp.models.FoodDonationHelperClass;
import com.example.foodapp.models.Order;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class MyOrdersFragment extends Fragment {

    RecyclerView my_order_recyclerView;
    ArrayList<Order> my_order_list;
    RecyclerView.LayoutManager layoutManager;
    My_orders_adapter adapter;
    FirebaseUser user;

    DatabaseReference reference;
    private FirebaseAuth mAuth;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_my_orders, container, false);
        my_order_recyclerView = v.findViewById(R.id.my_order_recycler);
        my_order_list = new ArrayList<>();
        user = FirebaseAuth.getInstance().getCurrentUser();
        mAuth = FirebaseAuth.getInstance();

        getDataFromFireBase();

        adapter = new My_orders_adapter(my_order_list);

        layoutManager=new LinearLayoutManager(getContext());
        my_order_recyclerView.setLayoutManager(layoutManager);
        my_order_recyclerView.setAdapter(adapter);
        my_order_recyclerView.setHasFixedSize(true);

        return v;
    }

    private void getDataFromFireBase() {

        reference = FirebaseDatabase.getInstance().getReference().child("orders");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                my_order_list.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    if (snapshot.child("user_id").getValue().toString().equals(user.getUid()) && snapshot.child("status").getValue().toString().equals("available")) {
                        Order order = new Order();
                        order.setId(snapshot.child("id").getValue().toString());
                        order.setDonation_id(snapshot.child("donation_id").getValue().toString());
                        order.setUser_id(snapshot.child("user_id").getValue().toString());
                        order.setCreated_at(snapshot.child("created_at").getValue().toString());
                        order.setStatus(snapshot.child("created_at").getValue().toString());
                        my_order_list.add(order);

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