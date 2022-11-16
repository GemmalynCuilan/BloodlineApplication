package com.example.bloodlineapplication.update;

public class DonorData {

    private int TotalDonate;
    private String LastDonate, Name, Email, UID, Address;


    public DonorData() {

    }
    public DonorData(String Name, String Email, String Address){
        this.Name= Name;
        this.Email= Email;
        this.Address = Address;
    }
    public String getName(){
        return Name;
    }
    public void setName(String name){
        this.Name = name;
    }
    public String getEmail(){
        return Email;
    }
    public void setEmail(String email){
        this.Email = email;
    }
    public String getAddress(){
        return Address;
    }
    public void setAddress(String address){
        this.Address = address;
    }
}