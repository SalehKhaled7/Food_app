package com.example.foodapp.models;

import com.example.foodapp.models.AddresshelperClass;

import java.util.ArrayList;

public class FoodDonationHelperClass {
    private String id,userID,title,description,createdAt,amount;
    //private ArrayList<String> address;
    private ArrayList<String> imageList;
    AddresshelperClass address ;
    public FoodDonationHelperClass(){

    }

    public FoodDonationHelperClass(String id, String userID, String title, String description, String createdAt, String amount, ArrayList<String> imageList, AddresshelperClass address) {
        this.id = id;
        this.userID = userID;
        this.title = title;
        this.description = description;
        this.createdAt = createdAt;
        this.amount = amount;
        this.imageList = imageList;
        this.address = address;
    }

    public ArrayList<String> getImage() {
        return imageList;
    }

    public void setImageList(ArrayList<String> imageList) {
        this.imageList = imageList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AddresshelperClass getAddress() {
        return address;
    }

    public void setAddress(AddresshelperClass address) {
        this.address = address;
    }

    public ArrayList<String> getImageList() {
        return imageList;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
