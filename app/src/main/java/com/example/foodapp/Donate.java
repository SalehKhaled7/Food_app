package com.example.foodapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.foodapp.models.AddresshelperClass;
import com.example.foodapp.models.FoodDonationHelperClass;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.Calendar;

public class Donate extends AppCompatActivity {


    TextInputLayout donationTitle, donationDescription, donationAmount,donationCity,donationDistrict,donationAddressDetails;

    ImageView addImageBtn;
    String id;
    private static final int RESULT_LOAD_IMAGE = 1;
    RecyclerView mRecyclerView;
    RecyclerView.Adapter mAdapter;
    LinearLayoutManager mLayoutManager;
    ArrayList<ImageItem> imageItemList;

    FirebaseDatabase rootNode;
    DatabaseReference reference,reference2;
    private FirebaseAuth mAuth;
    private StorageReference mStorageRef;

    Button DonationListBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_donate);

        donationTitle = findViewById(R.id.donation_title);
        donationDescription = findViewById(R.id.donation_description);
        donationAmount = findViewById(R.id.donation_amount);
        donationCity = findViewById(R.id.donation_city);
        donationDistrict = findViewById(R.id.donation_district);
        donationAddressDetails = findViewById(R.id.donation_home_address);

        addImageBtn = findViewById(R.id.add_img_btn);
        imageItemList = new ArrayList<>();
        mRecyclerView = findViewById(R.id.img_recycler);

        DonationListBtn = findViewById(R.id.donation_list_btn);



        addImageBtn.setOnClickListener(new View.OnClickListener() { // select images from gallery
            @Override
            public void onClick(View v) {

                mRecyclerView.setHasFixedSize(true);
                mLayoutManager = new LinearLayoutManager(Donate.this, LinearLayoutManager.HORIZONTAL, false);
                mAdapter = new DonationImageListAdapter(imageItemList);
                mRecyclerView.setLayoutManager(mLayoutManager);
                mRecyclerView.setAdapter(mAdapter);

                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent,"select images"), RESULT_LOAD_IMAGE);
            }
        });

        DonationListBtn.setOnClickListener(new View.OnClickListener() { // push the donations to firebase
            @Override
            public void onClick(View v) {

                mAuth = FirebaseAuth.getInstance();
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("donations");//donations is the table that we want to add the data to

                //id,userID,title,description,address,createdAt,amount
                id = rootNode.getReference("donations").push().getKey(); // make UID for donation
                String userID =mAuth.getUid(); // get current user ID
                String title=donationTitle.getEditText().getText().toString();
                String description=donationDescription.getEditText().getText().toString();
               // String address="default address";
                String createdAt = Calendar.getInstance().getTime().toString(); //current time
                String amount=donationAmount.getEditText().getText().toString();
                String city=donationCity.getEditText().getText().toString();
                String district=donationDistrict.getEditText().getText().toString();
                String addressDetail=donationAddressDetails.getEditText().getText().toString();
                AddresshelperClass address = new AddresshelperClass(city,district,addressDetail);
                ArrayList<ImageItem> imageList = new ArrayList<>();
                ArrayList<String> imageUriList = new ArrayList<>();

                String folder = id+"/";
                mStorageRef = FirebaseStorage.getInstance().getReference();


                for (int i =0 ; i<imageItemList.size();i++){

                    Uri imageUri = imageItemList.get(i).imageUri;
                    StorageReference riversRef = mStorageRef.child(folder+i);
                    UploadTask uploadTask=riversRef.putFile(imageUri);
                    uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            riversRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    imageUriList.add(uri.toString());
                                    reference.child(id).child("images").setValue(imageUriList);
                                    Toast.makeText(getApplicationContext(),uri.toString(),Toast.LENGTH_SHORT).show();
                                }
                            });


                            }


                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(),"bad",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                FoodDonationHelperClass donation = new FoodDonationHelperClass(id,userID,title,description,createdAt,amount,imageUriList,address);
                reference.child(id).setValue(donation); // id number is PK



            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) { // data retrieve from gallery
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK) {
            if (data.getClipData() != null) { // if the user selected more than one item ,

                int totalItemSelected = data.getClipData().getItemCount();
                //
                for (int i = 0; i < totalItemSelected; i++){
                    //pass the items to the list for recycler view

                    Uri imageUri = data.getClipData().getItemAt(i).getUri();

                    int donationID = 0;
                    ImageItem imageItem = new ImageItem(imageUri);
                    imageItemList.add(imageItem);
                }
                mAdapter.notifyDataSetChanged();

            } else if (data.getData() != null) {
                Uri imageUri = data.getData();
                imageItemList.add(new ImageItem(imageUri));
                mAdapter.notifyDataSetChanged();
            }
        }
    }


}