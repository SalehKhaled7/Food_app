package com.example.foodapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodapp.models.UserHelperClass;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileFragment2 extends Fragment {


    DatabaseReference reference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile2, container, false);
        ImageView user_image = view.findViewById(R.id.user_image);
        TextInputLayout user_display_name = view.findViewById(R.id.user_name_display_tv);
        TextInputLayout user_Phone = view.findViewById(R.id.user_phone_tv);
        Button user_info_update = view.findViewById(R.id.user_info_update);

        UserHelperClass userHelperClass = new UserHelperClass();


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        reference = FirebaseDatabase.getInstance().getReference().child("users").child(user.getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                user_Phone.getEditText().setText(snapshot.child("phoneNumber").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        if (user != null){
            if (user.getDisplayName() != null){
                user_display_name.getEditText().setText(user.getDisplayName());
            }
        }


        user_info_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reference.child("phoneNumber").setValue(user_Phone.getEditText().getText().toString());
                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                        .setDisplayName(user_display_name.getEditText().getText().toString()).build();

                user.updateProfile(profileUpdates)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getContext(), "profile updated successfully", Toast.LENGTH_SHORT).show();;
                                }
                            }
                        });


            }
        });








        // Inflate the layout for this fragment
        return view;
    }
}