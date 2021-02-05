package com.example.foodapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.foodapp.adapters.Delivery_adapter;
import com.example.foodapp.adapters.My_donations_adapter;
import com.example.foodapp.models.Delivery;
import com.example.foodapp.models.FoodDonationHelperClass;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class DeliveryFragment extends Fragment implements Delivery_adapter.OnDeliveryListener {

    RecyclerView delivery_RecyclerView;
    ArrayList<Delivery> delivery_list;
    RecyclerView.LayoutManager layoutManager;
    Delivery_adapter adapter;
    FirebaseUser user;

    DatabaseReference reference;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_delivery, container, false);
        delivery_RecyclerView = v.findViewById(R.id.delivery_rv);
        delivery_list = new ArrayList<>();
        user = FirebaseAuth.getInstance().getCurrentUser();

        getDataFromFireBase();
        adapter = new Delivery_adapter(delivery_list,this);
        layoutManager = new LinearLayoutManager(getContext());
        delivery_RecyclerView.setLayoutManager(layoutManager);
        delivery_RecyclerView.setAdapter(adapter);
        delivery_RecyclerView.setHasFixedSize(true);
        return v;
    }

    private void getDataFromFireBase() {
        reference = FirebaseDatabase.getInstance().getReference().child("deliveries");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                delivery_list.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    if (snapshot.child("status").getValue().toString().equals("new")){
                        Delivery delivery =new Delivery();
                        delivery.setId(snapshot.child("id").getValue().toString());
                        delivery.setTitle(snapshot.child("title").getValue().toString());
                        delivery.setFrom(snapshot.child("from").getValue().toString());
                        delivery.setTo(snapshot.child("to").getValue().toString());
                        delivery.setDelivered_by(snapshot.child("delivered_by").getValue().toString());
                        delivery.setDelivered_at(snapshot.child("delivered_at").getValue().toString());
                        delivery.setStatus(snapshot.child("status").getValue().toString());
                        delivery.setCreated_at(snapshot.child("created_at").getValue().toString());
                        delivery_list.add(delivery);
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onDeliveryClick(int position) {
        Intent intent = new Intent(getContext(), Delivery_details.class);
        intent.putExtra("delivery", (Parcelable) delivery_list.get(position));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        startActivity(intent);


    }
}