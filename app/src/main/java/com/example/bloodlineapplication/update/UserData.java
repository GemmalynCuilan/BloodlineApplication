package com.example.bloodlineapplication.update;
public class UserData {

    private String Name, Email, Address;
    private int Blood, BloodGroups;

    public UserData() {

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
    public int getBloodGroups(){
        return BloodGroups;
    }
    public void setBloodGroups(int bloodGroups){
        this.BloodGroups = bloodGroups;
    }
    public int getBlood(){
        return Blood;
    }
    public void setBlood(int blood){
        this.Blood = blood;
    }
}