package com.example.foodapp.models;

public class AddresshelperClass {
    String city,district,homeDetails;

    public AddresshelperClass() {
    }

    public AddresshelperClass(String city, String district, String homeDetails) {
        this.city = city;
        this.district = district;
        this.homeDetails = homeDetails;
    }

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
}
