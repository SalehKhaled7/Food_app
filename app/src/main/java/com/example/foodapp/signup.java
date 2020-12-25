package com.example.foodapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class signup extends AppCompatActivity {
        //update
    //variables
    TextInputLayout regName, regEmail, regPhone, regPass;
    RadioGroup regRadioGroup;
    RadioButton regType;
    Button regButton;

    private FirebaseAuth mAuth;
    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();// hide the action bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); //hide the status bar

        setContentView(R.layout.activity_signup);

        //connect variables with elements in signup activity
        regName = findViewById(R.id.reg_name);
        regEmail = findViewById(R.id.reg_email);
        regPhone = findViewById(R.id.reg_phone);
        regPass = findViewById(R.id.reg_pass);
        regRadioGroup = findViewById(R.id.reg_radio_grp);
        regButton = findViewById(R.id.reg_btn);
        mAuth = FirebaseAuth.getInstance();

        //save data in firebase on click button

//        regButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                rootNode = FirebaseDatabase.getInstance();
//                reference = rootNode.getReference("users");//user is the table that we want to add the data to
//
//                //get all the values
//                String name = regName.getEditText().getText().toString();
//                String email = regEmail.getEditText().getText().toString();
//                int phoneNumber = Integer.parseInt(regPhone.getEditText().getText().toString());
//                String passWord = regPass.getEditText().getText().toString();
//                String type = checkButton();
//                String address = "default address";
//                int donations = 0;
//                int donationReceived= 0;
//                String id = String.valueOf(phoneNumber);
//
//
//                UserHelperClass helperClass =new UserHelperClass(name,email,phoneNumber,passWord,type,address,donations,donationReceived);
//                reference.child(id).setValue(helperClass); // phone number is UK
//            }
//        });
    }

    private Boolean validateName() {

        String val = regName.getEditText().getText().toString();
        if (val.isEmpty()) {
            regName.setError("name can't be empty");
            return false;
        } else if (val.length() <= 4) {
            regName.setError("name is too short");
            return false;
        } else if (val.length() >= 25) {
            regName.setError("name is too long");
            return false;
        } else {
            regName.setError(null);
            regName.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateEmail() {

        String val = regEmail.getEditText().getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (val.isEmpty()) {
            regEmail.setError("Email can't be empty");
            return false;
        } else if (!val.matches(emailPattern)) {
            regEmail.setError("Invalid email address");
            return false;
        } else {
            regEmail.setError(null);
            regEmail.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatePhoneNumber() {

        String val = regPhone.getEditText().getText().toString();
        if (val.isEmpty()) {
            regPhone.setError("Phone number can't be empty");
            return false;
        } else {
            regPhone.setError(null);
            regPhone.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatePassword() {

        String val = regPass.getEditText().getText().toString();
        String passwordVal = "^" +
                //"(?=.*[0-9])" +         //at least 1 digit
                //"(?=.*[a-z])" +         //at least 1 lower case letter
                //"(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                //"(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=\\S+$)" +           //no white spaces
                ".{4,}" +               //at least 4 characters
                "$";
        if (val.isEmpty()) {
            regPass.setError("password can't be empty");
            return false;
        } else if (!val.matches(passwordVal)) {
            regPass.setError("Invalid password");
            return false;
        } else {
            regPass.setError(null);
            regPass.setErrorEnabled(false);
            return true;
        }
    }

    public void registerUser(View view) {//save the data to fire base
        if (!validateName() | !validateEmail() | !validatePhoneNumber() | !validatePassword()) {
            return;
        }

        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("users");//user is the table that we want to add the data to

        //get all the values
        String email = regEmail.getEditText().getText().toString().trim();
        String passWord = regPass.getEditText().getText().toString().trim();

        mAuth.createUserWithEmailAndPassword(email, passWord).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {//if registration done successfully

                    String id = mAuth.getUid();
                    String name = regName.getEditText().getText().toString();
                    String phoneNumber = regPhone.getEditText().getText().toString();
                    String type = checkButton();
                    String address = "default address";
                    String donations = "0";
                    String donationReceived = "0";
                    UserHelperClass helperClass = new UserHelperClass(id, name, phoneNumber, type, address, donations, donationReceived);
                    reference.child(id).setValue(helperClass); // id number is PK
                    Intent intent = new Intent(getApplicationContext(), Home.class);
                    startActivity(intent);
                } else {
                    if (task.getException() instanceof FirebaseAuthUserCollisionException) {//if the email already registered
                        Toast.makeText(signup.this, "Email already registered", Toast.LENGTH_LONG).show();
                        regEmail.requestFocus();
                    } else {
                        Toast.makeText(signup.this, "Something went wrong please try again later", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


//        UserHelperClass helperClass =new UserHelperClass(name,email,phoneNumber,passWord,type,address,donations,donationReceived);
//        reference.child(email).setValue(helperClass); // phone number is PK
//
    }

    public String checkButton() {//see what radio button did the user chose
        int radioId = regRadioGroup.getCheckedRadioButtonId();
        regType = findViewById(radioId);
        return regType.getText().toString();

    }
}