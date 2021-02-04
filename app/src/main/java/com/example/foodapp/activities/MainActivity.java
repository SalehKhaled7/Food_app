package com.example.foodapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodapp.Home;
import com.example.foodapp.R;
import com.example.foodapp.activities.signup;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    //variables
    TextInputLayout logEmail, logPass;
    Button logButton, logForgot;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();// hide the action bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN); //hide the status bar

        setContentView(R.layout.activity_main);
        //connect variables with elements in login activity

        logEmail = findViewById(R.id.login_email);
        logPass = findViewById(R.id.login_password);
        logButton = findViewById(R.id.login_btn);
        mAuth =FirebaseAuth.getInstance();

        TextView open_sign_up = findViewById(R.id.tv_create_new_account);
        open_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSignUpActivity();

            }
        });

    }

    public void openSignUpActivity() {
        Intent intent = new Intent(getApplicationContext(), signup.class);
        startActivity(intent);
    }

    private Boolean validateEmail() {

        String val = logEmail.getEditText().getText().toString();

        if (val.isEmpty()) {
            logEmail.setError("Email can't be empty");
            return false;
        } else {
            logEmail.setError(null);
            logEmail.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatePassword() {

        String val = logPass.getEditText().getText().toString();
        if (val.isEmpty()) {
            logPass.setError("password can't be empty");
            return false;
        } else {
            logPass.setError(null);
            logPass.setErrorEnabled(false);
            return true;
        }
    }

    public void loginUser(View view) {
        if (!validateEmail() | !validatePassword()) {
            return;
        }else{
            isUser();
        }
    }

    private void isUser(){
        String userEnteredEmail = logEmail.getEditText().getText().toString().trim();
        String userEnteredPass = logPass.getEditText().getText().toString().trim();

        mAuth.signInWithEmailAndPassword(userEnteredEmail,userEnteredPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Intent intent = new Intent(getApplicationContext(), Home.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(getBaseContext(), task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
//        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
//        Query checkUser = reference.orderByChild("email").equalTo(userEnteredEmail);
//        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                //check if the user is registered in database
//                if(snapshot.exists()){
//
//                    logEmail.setError(null);//set email error msg tp null if user tried again
//                    logEmail.setErrorEnabled(false); // remove error msg
//
//                    String passwordFromDB = snapshot.child(userEnteredEmail).child("passWord").getValue(String.class);
//
//                    //check if the password is correct
//
//                    if (passwordFromDB != null) {
//                        if(passwordFromDB.equals(userEnteredPass)){
//
//                            logPass.setError(null);//set password error msg tp null if user tried again
//                            logPass.setErrorEnabled(false); // remove error msg
//
//                            //get user data from database
//                            String nameFromDB = snapshot.child(userEnteredEmail).child("name").getValue(String.class);
//                            String emailFromDB = snapshot.child(userEnteredEmail).child("email").getValue(String.class);
//                            String phoneFromDB = snapshot.child(userEnteredEmail).child("phoneNumber").getValue(String.class);
//                            String typeFromDB = snapshot.child(userEnteredEmail).child("type").getValue(String.class);
//                            String addressFromDB = snapshot.child(userEnteredEmail).child("address").getValue(String.class);
//                            String donationsFromDB = snapshot.child(userEnteredEmail).child("donations").getValue(String.class);
//                            String receivedDonationsFromDB = snapshot.child(userEnteredEmail).child("donation_received").getValue(String.class);
//
//                            //pass user data to next activity
//                            Intent intent = new Intent(getApplicationContext(), Home.class);
//                            intent.putExtra("name",nameFromDB);
//                            intent.putExtra("email",emailFromDB);
//                            intent.putExtra("phone",phoneFromDB);
//                            intent.putExtra("passWord",passwordFromDB);
//                            intent.putExtra("type",typeFromDB);
//                            intent.putExtra("address",addressFromDB);
//                            intent.putExtra("donations",donationsFromDB);
//                            intent.putExtra("donation_received",receivedDonationsFromDB);
//                            startActivity(intent);
//                        }
//                        else {  //if the user entered wrong password
//                            logPass.setError("Wrong Password");
//                            logPass.requestFocus();
//                        }
//                    }
//                }
//                else {  //if the user entered wrong email
//                    logEmail.setError("Email in not registered");
//                    logEmail.requestFocus();
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
    }
}