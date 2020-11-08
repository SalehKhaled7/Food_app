package com.example.foodapp;

public class FoodDonationHelperClass {
    private String id,userID,title,description,address,createdAt,amount;

    public FoodDonationHelperClass(){

    }

    public FoodDonationHelperClass(String id, String userID, String title, String description, String address, String createdAt, String amount) {
        this.id = id;
        this.userID = userID;
        this.title = title;
        this.description = description;
        this.address = address;
        this.createdAt = createdAt;
        this.amount = amount;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
