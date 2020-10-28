package com.example.foodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class signup extends AppCompatActivity {

    //variables
    TextInputLayout regName,regEmail,regPhone,regPass;
    RadioGroup regRadioGroup;
    RadioButton regType;
    Button regButton;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        //connect variables with elements in signup activity
        regName=findViewById(R.id.reg_name);
        regEmail=findViewById(R.id.reg_email);
        regEmail=findViewById(R.id.reg_email);
        regPhone=findViewById(R.id.reg_phone);
        regPass=findViewById(R.id.reg_pass);
        regRadioGroup=findViewById(R.id.reg_radio_grp);
        regButton=findViewById(R.id.reg_btn);

        //save data in firebase on click button
        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("users");//user is the table that we want to add the data to

                //get all the values
                String name = regName.getEditText().getText().toString();
                String email = regEmail.getEditText().getText().toString();
                int phoneNumber = Integer.parseInt(regPhone.getEditText().getText().toString());
                String passWord = regPass.getEditText().getText().toString();
                String type = checkButton();
                String address = "default address";
                int donations = 0;
                int donationReceived= 0;
                String id = String.valueOf(phoneNumber);


                UserHelperClass helperClass =new UserHelperClass(name,email,phoneNumber,passWord,type,address,donations,donationReceived);
                reference.child(id).setValue(helperClass); // phone number is UK
            }
        });
    }
    public String checkButton(){//see what radio button did the user chose
        int radioId =regRadioGroup.getCheckedRadioButtonId();
        regType = findViewById(radioId);
        return regType.getText().toString();

    }
}