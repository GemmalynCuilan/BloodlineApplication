package com.example.bloodlineapplication.update;

import android.widget.EditText;
import android.widget.Spinner;

import java.io.Serializable;

public class UserReq implements Serializable {
    private String SerialNumber, Address;
    private String BloodGroups, Blood;
    private String Time, Date;

    public UserReq(String serialNumber, String address, String bloodGroups, String blood, String time, String date) {
        this.SerialNumber = serialNumber;
        this.Address = address;
        this.BloodGroups = bloodGroups;
        this.Blood = blood;
        this.Time = time;
        this.Date = date;
    }

    public String getSerialNumber() {
        return SerialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        SerialNumber = serialNumber;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getBloodGroups() {
        return BloodGroups;
    }

    public void setBloodGroups(String bloodGroups) {
        BloodGroups = bloodGroups;
    }
    public String getBlood() {
        return Blood;
    }

    public void setBlood(String blood) {
        Date = blood;
    }
    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public UserReq() {

    }
}