package com.example.bloodlineapplication.update;

import android.widget.EditText;
import android.widget.Spinner;

import java.io.Serializable;

public class CustomUserData implements Serializable {
    private String SerialNumber, Address;
    private String  BloodGroups;




    public CustomUserData(EditText serialnumber, EditText address, Spinner bloodGroups) {

    }

    public CustomUserData(String serialnumber, String address, String bloodGroups) {
        SerialNumber = serialnumber;
        Address = address;
        BloodGroups = bloodGroups;

    }
    public String getSerialNumber() {
        return SerialNumber;
    }

    public void setSerialNumber(String serialnumber) {
        SerialNumber = serialnumber;
    }
    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        this.Address = address;
    }

    public String getBloodGroups() {
        return BloodGroups;
    }

    public void setBloodGroups(String bloodGroups) {
        this.BloodGroups = bloodGroups;
    }

}
