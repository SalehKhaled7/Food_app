package com.example.foodapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.R;
import com.example.foodapp.models.Delivery;


import java.util.ArrayList;

public class Delivery_adapter extends RecyclerView.Adapter<Delivery_adapter.MyView_Holder> {

    final ArrayList<Delivery> delivery_list;



    public class MyView_Holder extends RecyclerView.ViewHolder {
        public TextView title,from_address,to_address;
        public MyView_Holder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.delivery_item_title);
            from_address = itemView.findViewById(R.id.delivery_item_from);
            to_address = itemView.findViewById(R.id.delivery_item_to);

        }
    }

    public Delivery_adapter(ArrayList<Delivery> delivery_list) {
        this.delivery_list = delivery_list;
    }

    @NonNull
    @Override
    public MyView_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.delivery_item,parent,false);
        return  new MyView_Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyView_Holder holder, int position) {
        Delivery currentDelivery = delivery_list.get(position);
        holder.from_address.setText(currentDelivery.getFrom());
        holder.to_address.setText(currentDelivery.getTo());


    }

    @Override
    public int getItemCount() {
        return delivery_list.size();
    }


}
