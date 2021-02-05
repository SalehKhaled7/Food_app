package com.example.foodapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodapp.adapters.FoodDetailImagesAdapter;
import com.example.foodapp.models.Delivery;
import com.example.foodapp.models.FoodDonationHelperClass;
import com.example.foodapp.models.Order;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.Calendar;

public class FoodDetail extends AppCompatActivity {
    TextView mTitleTv,mDescTv,mAmountTv;
    ImageView mImageTv;
    RecyclerView img_recyclerView;
    Button order_btn;
    private FirebaseAuth mAuth;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    DatabaseReference reference2;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();// hide the action bar

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);

        //actionbar
        ActionBar actionBar = getSupportActionBar();

        mTitleTv = findViewById(R.id.dTitleTv);
        mDescTv = findViewById(R.id.dDescrTv);
        mImageTv = findViewById(R.id.dImageIv);
        mAmountTv=findViewById(R.id.damountTv);
        img_recyclerView = findViewById(R.id.food_detail_img_recycler_view);
        order_btn = findViewById(R.id.order_btn);



        //intent
        Bundle data = getIntent().getExtras();
        FoodDonationHelperClass donation = data.getParcelable("donation");
        FoodDetailImagesAdapter adapter = new FoodDetailImagesAdapter(getApplicationContext(),donation.getImageList());
        img_recyclerView.setAdapter(adapter);
        img_recyclerView.setLayoutManager(new LinearLayoutManager(this));
        String mImage = donation.getImageList().get(0);
        String mDescr = donation.getDescription();
        String mTitle = donation.getTitle();
        String mAmount = "for: "+donation.getAmount()+" people";
        //set image
        Picasso.get().load(mImage).into(mImageTv);

        //set title to actionbar
        actionBar.setTitle(mTitle);

        //set data to our views
        mTitleTv.setText(mTitle);
       // mImageTv.setImageBitmap(bitmap);
        mDescTv.setText(mDescr);
        mAmountTv.setText(mAmount);

        order_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                make_order();
            }


            private void make_order() {

                mAuth = FirebaseAuth.getInstance();
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("orders");//donations is the table that we want to add the data to

                id = rootNode.getReference("donations").push().getKey(); // make UID for order
                String donation_id = donation.getId();
                String user_id = mAuth.getUid();
                String created_at = Calendar.getInstance().getTime().toString();
                String status = "available";
                Order order = new Order(id,donation_id,user_id,created_at,status);
                reference.child(id).setValue(order); // id number is PK

                reference2 = rootNode.getReference("deliveries");//donations is the table that we want to add the data to

                String delivery_id = rootNode.getReference("deliveries").push().getKey(); // make UID for order
                String delivery_title = donation.getTitle();
                String from = donation.getUserID();
                String to = mAuth.getUid();
                String delivery_status = "new";
                String delivered_by =" ";
                String delivered_at =" ";
                String delivery_created_At = Calendar.getInstance().getTime().toString();
                Delivery delivery = new Delivery(delivery_id,id,delivery_title,from,to,delivered_by,delivered_at,delivery_status,delivery_created_At);
                reference2.child(delivery_id).setValue(delivery); // id number is PK

                Toast.makeText(getApplicationContext(),"your order is made check the basked ",Toast.LENGTH_SHORT).show();
                finish();

            }

        });
    }




}