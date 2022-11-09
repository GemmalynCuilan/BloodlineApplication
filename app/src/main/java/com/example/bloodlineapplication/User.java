package com.example.bloodlineapplication;

public class User {
    public String name, userAddress, emailAddress, pass, bloodDr, bgroup;

    public User(){

    }
    public User(String name,String userAddress, String emailAddress, String pass,String bloodDr, String bgroup){
        this.name = name;
        this.userAddress = userAddress;
        this.emailAddress = emailAddress;
        this.pass = pass;
        this.bloodDr = bloodDr;
        this.bgroup = bgroup;

    }
}
