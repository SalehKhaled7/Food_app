package com.example.foodapp;

public class UserHelperClass {

    private String name ,email,PassWord,type,address,phoneNumber,donations,donation_received;


    public UserHelperClass() {
    }

    public UserHelperClass(String name, String email, String phoneNumber, String passWord, String type, String address, String donations, String donation_received) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.PassWord = passWord;
        this.type = type;
        this.address = address;
        this.donations = donations;
        this.donation_received = donation_received;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
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


}
