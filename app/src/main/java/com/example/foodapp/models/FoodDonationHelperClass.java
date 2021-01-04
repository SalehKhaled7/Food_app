package com.example.foodapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.foodapp.models.AddresshelperClass;

import java.util.ArrayList;

public class FoodDonationHelperClass implements Parcelable {
    private String id,userID,title,description,createdAt,amount;
    //private ArrayList<String> address;
    private ArrayList<String> imageList;
    private AddresshelperClass address = new AddresshelperClass();
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

    protected FoodDonationHelperClass(Parcel in) {
        id = in.readString();
        userID = in.readString();
        title = in.readString();
        description = in.readString();
        createdAt = in.readString();
        amount = in.readString();
        imageList = in.createStringArrayList();
        address = in.readParcelable(AddresshelperClass.class.getClassLoader());
    }

    public static final Creator<FoodDonationHelperClass> CREATOR = new Creator<FoodDonationHelperClass>() {
        @Override
        public FoodDonationHelperClass createFromParcel(Parcel in) {
            return new FoodDonationHelperClass(in);
        }

        @Override
        public FoodDonationHelperClass[] newArray(int size) {
            return new FoodDonationHelperClass[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(userID);
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(createdAt);
        dest.writeString(amount);
        dest.writeStringList(imageList);
        dest.writeParcelable(address, flags);
    }
}
