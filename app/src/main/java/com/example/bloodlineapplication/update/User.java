package com.example.bloodlineapplication.update;

public class User {
    public String name, userAddress, emailAdd,  phone,pass,  bgroup, bloodDr;

    public User() {

    }
    public User(String name, String userAddress, String emailAdd, String phone, String bgroup, String bloodDr){
        this.name = name;
        this.userAddress = userAddress;
        this.emailAdd = emailAdd;
        this.phone = phone;
        this.pass = pass;
        this.bgroup = bgroup;
        this.bloodDr = bloodDr;
    }
        public String getName(){
                return name;
        }
        public void setName(String name){
            this.name = name;
        }
        public String getUserAddress(){
            return userAddress;
        }
        public void setUserAddress(String userAddress){
            this.userAddress = userAddress;
        }
        public String getEmailAdd(){
            return emailAdd;
        }
        public void setEmailAdd(String emailAdd){
            this.emailAdd = emailAdd;
        }
        public String getPhone(){
            return phone;
        }
        public void setPhone(String phone){
            this.phone = phone;
        }
        public String getPass(){
            return pass;
        }
        public void setPass(String pass){
            this.pass = pass;
        }
        public String getBgroup(){
            return bgroup;
        }
        public void setBgroup(String bgroup){
            this.bgroup = bgroup;
        }
        public String getBloodDr(){
            return bloodDr;
        }
        public void setBloodDr(String bloodDr){
            this.bloodDr = bloodDr;
        }
}
