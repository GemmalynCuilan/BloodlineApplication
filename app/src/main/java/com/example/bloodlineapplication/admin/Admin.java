package com.example.bloodlineapplication.admin;

public class Admin {
    public String userId, fullname, email, password;

    public Admin(){

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Admin(String userId, String fullname, String email, String password) {
        this.userId = userId;
        this.fullname = fullname;
        this.email = email;
        this.password = password;
    }
}
