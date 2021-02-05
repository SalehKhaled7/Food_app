package com.example.foodapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.IgnoreExtraProperties;


public class UserHelperClass implements Parcelable {

    private String id,name ,email,PassWord,type,phoneNumber,donations,donation_received;
    private AddresshelperClass address = new AddresshelperClass();


    public UserHelperClass() {
    }

    public UserHelperClass(String id,String name, String phoneNumber, String type, AddresshelperClass address, String donations, String donation_received) {
        this.id=id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.type = type;
        this.address = address;
        this.donations = donations;
        this.donation_received = donation_received;
    }

    protected UserHelperClass(Parcel in) {
        id = in.readString();
        name = in.readString();
        email = in.readString();
        PassWord = in.readString();
        type = in.readString();
        phoneNumber = in.readString();
        donations = in.readString();
        donation_received = in.readString();
        address = in.readParcelable(AddresshelperClass.class.getClassLoader());
    }

    public static final Creator<UserHelperClass> CREATOR = new Creator<UserHelperClass>() {
        @Override
        public UserHelperClass createFromParcel(Parcel in) {
            return new UserHelperClass(in);
        }

        @Override
        public UserHelperClass[] newArray(int size) {
            return new UserHelperClass[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassWord() {
        return PassWord;
    }

    public void setPassWord(String passWord) {
        PassWord = passWord;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public AddresshelperClass getAddress() {
        return address;
    }

    public void setAddress(AddresshelperClass address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDonations() {
        return donations;
    }

    public void setDonations(String donations) {
        this.donations = donations;
    }

    public String getDonation_received() {
        return donation_received;
    }

    public void setDonation_received(String donation_received) {
        this.donation_received = donation_received;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(email);
        dest.writeString(PassWord);
        dest.writeString(type);
        dest.writeString(phoneNumber);
        dest.writeString(donations);
        dest.writeString(donation_received);
        dest.writeParcelable(address, flags);
    }
}
