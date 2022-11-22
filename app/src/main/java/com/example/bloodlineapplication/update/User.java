package com.example.bloodlineapplication.update;

public class User {
    public String userId, fullname, houseAddress, email, phoneNumber ,password,  bloodGroups, blood, profileImage;

    public User() {

    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getHouseAddress() {
        return houseAddress;
    }

    public void setHouseAddress(String houseAddress) {
        this.houseAddress = houseAddress;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBloodGroups() {
        return bloodGroups;
    }

    public void setBloodGroups(String bloodGroups) {
        this.bloodGroups = bloodGroups;
    }

    public String getBlood() {
        return blood;
    }

    public void setBlood(String blood) {
        this.blood = blood;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public User(String userId, String fullname, String houseAddress, String email, String phoneNumber,
                String password, String bloodGroups, String blood, String profileImage) {
        this.userId = userId;
        this.fullname = fullname;
        this.houseAddress = houseAddress;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.bloodGroups = bloodGroups;
        this.blood = blood;
        this.profileImage = profileImage;
    }
}