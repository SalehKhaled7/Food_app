package com.example.foodapp.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.R;
import com.example.foodapp.models.Order;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class My_orders_adapter extends RecyclerView.Adapter<My_orders_adapter.MyView_Holder> {

     final ArrayList<Order> my_order_list;

    public  class MyView_Holder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView title , description,date;
        public MyView_Holder(@NonNull View itemView) {

            super(itemView);
            imageView = itemView.findViewById(R.id.order_img);
            title=itemView.findViewById(R.id.order_title);
            description=itemView.findViewById(R.id.order_desc);
            date=itemView.findViewById(R.id.order_date);

        }
    }

    public My_orders_adapter(ArrayList<Order> my_order_list){
        this.my_order_list=my_order_list;
    }

    @NonNull
    @Override
    public MyView_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_order_item,parent,false);
        return new MyView_Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyView_Holder holder, int position) {
        Order currentOrder = my_order_list.get(position);

        DatabaseReference reference;
        reference = FirebaseDatabase.getInstance().getReference().child("donations").child(currentOrder.getDonation_id());

        reference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String image = snapshot.child("images").child("0").getValue().toString();
                Picasso.get().load(image).into(holder.imageView);
                holder.title.setText(snapshot.child("title").getValue().toString());
                holder.description.setText(snapshot.child("description").getValue().toString());
                holder.date.setText("today");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });






    }

    @Override
    public int getItemCount() {
        return my_order_list.size();
    }


}
