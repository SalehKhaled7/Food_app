package com.example.foodapp.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.ItemClickListener;
import com.example.foodapp.R;
import com.example.foodapp.models.Delivery;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;

public class Delivery_adapter extends RecyclerView.Adapter<Delivery_adapter.MyView_Holder> {
    DatabaseReference user_from,user_to;
    String city_from,city_to,district_from,district_to;
    private OnDeliveryListener mOnDeliveryListener;



    final ArrayList<Delivery> delivery_list;



    public class MyView_Holder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView title,from_address,to_address;
        OnDeliveryListener onDeliveryListener;
        public MyView_Holder(@NonNull View itemView,OnDeliveryListener onDeliveryListener) {
            super(itemView);
            title = itemView.findViewById(R.id.delivery_item_title);
            from_address = itemView.findViewById(R.id.delivery_item_from);
            to_address = itemView.findViewById(R.id.delivery_item_to);
            this.onDeliveryListener=onDeliveryListener;
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            onDeliveryListener.onDeliveryClick(getAdapterPosition());

        }
    }

    public Delivery_adapter(ArrayList<Delivery> delivery_list, OnDeliveryListener onDeliveryListener) {
        this.delivery_list = delivery_list;
        this.mOnDeliveryListener = onDeliveryListener;
    }

    @NonNull
    @Override
    public MyView_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.delivery_item,parent,false);
        return  new MyView_Holder(view,mOnDeliveryListener);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyView_Holder holder, int position) {

        Delivery currentDelivery = delivery_list.get(position);
        holder.title.setText(currentDelivery.getTitle());
        user_from = FirebaseDatabase.getInstance().getReference().child("users").child(currentDelivery.getFrom());
        user_from.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                city_from = snapshot.child("address").child("city").getValue().toString();
                district_from = snapshot.child("address").child("district").getValue().toString();
                holder.from_address.setText(city_from+","+district_from);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        user_to = FirebaseDatabase.getInstance().getReference().child("users").child(currentDelivery.getTo());
        user_to.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                city_to = snapshot.child("address").child("city").getValue().toString();
                district_to = snapshot.child("address").child("district").getValue().toString();
                holder.to_address.setText(city_to+","+district_to);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }

    @Override
    public int getItemCount() {
        return delivery_list.size();
    }
    public interface OnDeliveryListener{
        void onDeliveryClick(int position);
    }


}
