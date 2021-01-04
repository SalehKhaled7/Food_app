package com.example.foodapp.models;

import android.os.Parcel;
import android.os.Parcelable;

public class AddresshelperClass implements Parcelable {
    String city,district,homeDetails;

    public AddresshelperClass() {
    }

    public AddresshelperClass(String city, String district, String homeDetails) {
        this.city = city;
        this.district = district;
        this.homeDetails = homeDetails;
    }

    protected AddresshelperClass(Parcel in) {
        city = in.readString();
        district = in.readString();
        homeDetails = in.readString();
    }

    public static final Creator<AddresshelperClass> CREATOR = new Creator<AddresshelperClass>() {
        @Override
        public AddresshelperClass createFromParcel(Parcel in) {
            return new AddresshelperClass(in);
        }

        @Override
        public AddresshelperClass[] newArray(int size) {
            return new AddresshelperClass[size];
        }
    };

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getHomeDetails() {
        return homeDetails;
    }

    public void setHomeDetails(String homeDetails) {
        this.homeDetails = homeDetails;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(city);
        dest.writeString(district);
        dest.writeString(homeDetails);
    }
}
