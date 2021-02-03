package com.example.foodapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.R;
import com.example.foodapp.models.FoodDonationHelperClass;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class My_donations_adapter extends RecyclerView.Adapter<My_donations_adapter.MyView_Holder> {
    final ArrayList<FoodDonationHelperClass> my_donation_list;

    public class MyView_Holder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView title,description;
        public MyView_Holder(@NonNull View itemView) {

            super(itemView);
            imageView = itemView.findViewById(R.id.my_donation_item_img);
            title = itemView.findViewById(R.id.my_donation_item_title);
            description = itemView.findViewById(R.id.my_donation_item_desc);
        }
    }

    public My_donations_adapter(ArrayList<FoodDonationHelperClass> my_donation_list) {
        this.my_donation_list = my_donation_list;
    }

    @NonNull
    @Override
    public MyView_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_donation_item,parent,false);
        return  new MyView_Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyView_Holder holder, int position) {
        FoodDonationHelperClass currentDonation = my_donation_list.get(position);

        DatabaseReference reference ;
        reference = FirebaseDatabase.getInstance().getReference().child("donations").child(currentDonation.getId());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String image = snapshot.child("images").child("0").getValue().toString();
                Picasso.get().load(image).into(holder.imageView);
                holder.title.setText(snapshot.child("title").getValue().toString());
                holder.description.setText(snapshot.child("description").getValue().toString());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return my_donation_list.size();
    }


}
