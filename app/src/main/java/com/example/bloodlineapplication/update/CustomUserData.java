package com.example.bloodlineapplication.update;

import java.io.Serializable;

public class CustomUserData implements Serializable {

    private String Name,  Email, Address, BloodGroups, Blood;

    public CustomUserData() {

    }
public CustomUserData(String address, String name, String email, String bloodGroups,String blood){
        Name = name;
        Address = address;
        Email = email;
        BloodGroups = bloodGroups;
        Blood = blood;
}
        public String getName(){
                return Name;
        }
        public void setName(String name){
                this.Name = name;
        }
        public String getAddress(){
                return Address;
        }
        public void setAddress(String address){
                this.Address = address;
        }
        public String getEmail(){
            return Email;
        }
        public void setEmail(String email){
            this.Email = email;
        }
        public String getBloodGroups(){
            return BloodGroups;
        }
        public void setBloodGroups(String bloodGroups){
            this.BloodGroups = bloodGroups;
        }
        public String getBlood(){
            return Blood;
        }
        public void setBlood(String blood){
            this.Blood = blood;
        }
        }