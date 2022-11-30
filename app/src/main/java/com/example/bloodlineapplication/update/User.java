package com.example.bloodlineapplication.update;

public class User {
    public String userId, fullname, address, email, age ,password,  bloodGroups, blood, profileImage;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
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

    public User(String userId, String fullname, String address, String email, String age,
                String password, String bloodGroups, String blood, String profileImage) {
        this.userId = userId;
        this.fullname = fullname;
        this.address = address;
        this.email = email;
        this.age = age;
        this.password = password;
        this.bloodGroups = bloodGroups;
        this.blood = blood;
        this.profileImage = profileImage;
    }
}