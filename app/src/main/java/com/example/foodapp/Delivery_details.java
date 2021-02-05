package com.example.foodapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodapp.models.Delivery;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class Delivery_details extends AppCompatActivity {
    DatabaseReference user_from,user_to ,delivery_ref,order_ref;
    TextView from_city , from_district,from_apt_detail ,to_city , to_district,to_apt_detail ,delivery_title;
    Button delivered_btn;
    String city_from,city_to,district_from,district_to,apt_from,apt_to;
    FirebaseUser curr_user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();// hide the action bar
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_details);

        ActionBar actionBar = getSupportActionBar();
        delivery_title = findViewById(R.id.delivery_detail_title);
        from_city = findViewById(R.id.delivery_detail_city_from);
        from_district = findViewById(R.id.delivery_detail_district_from);
        from_apt_detail = findViewById(R.id.delivery_detail_apt_from);
        to_city = findViewById(R.id.delivery_detail_city_to);
        to_district = findViewById(R.id.delivery_detail_district_to);
        to_apt_detail= findViewById(R.id.delivery_detail_apt_to);
        delivered_btn = findViewById(R.id.delivered_btn);
        curr_user = FirebaseAuth.getInstance().getCurrentUser();

        Bundle data = getIntent().getExtras();
        Delivery delivery = data.getParcelable("delivery");

        delivery_title.setText(delivery.getTitle());
        user_from = FirebaseDatabase.getInstance().getReference().child("users").child(delivery.getFrom());
        user_from.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                city_from = snapshot.child("address").child("city").getValue().toString();
                district_from = snapshot.child("address").child("district").getValue().toString();
                apt_from = snapshot.child("address").child("homeDetails").getValue().toString();
                from_city.setText(city_from);
                from_district.setText(district_from);
                from_apt_detail.setText(apt_from);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        user_to = FirebaseDatabase.getInstance().getReference().child("users").child(delivery.getTo());
        user_to.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                city_to = snapshot.child("address").child("city").getValue().toString();
                district_to = snapshot.child("address").child("district").getValue().toString();
                apt_to = snapshot.child("address").child("homeDetails").getValue().toString();
                to_city.setText(city_to);
                to_district.setText(district_to);
                to_apt_detail.setText(apt_to);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {


            }
        });





        delivered_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delivery_ref = FirebaseDatabase.getInstance().getReference().child("deliveries").child(delivery.getId());
                delivery_ref.child("status").setValue("delivered");
                delivery_ref.child("delivered_at").setValue(Calendar.getInstance().getTime().toString());
                delivery_ref.child("delivered_by").setValue(curr_user.getDisplayName());

                order_ref = FirebaseDatabase.getInstance().getReference().child("orders").child(delivery.getOrder_id());
                order_ref.child("status").setValue("done");
                Toast.makeText(getApplicationContext(),"thank you , for delivering",Toast.LENGTH_SHORT).show();
                finish();

            }
        });
    }
}