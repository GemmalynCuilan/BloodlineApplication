package com.example.bloodlineapplication.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bloodlineapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;


public class PostActivity extends AppCompatActivity {


    private ImageButton arrowBack;
    private Spinner bloodGroups;
    private EditText name, address, number;
    private TextView sname, add, bgrp, snum;
    private Button postbtn;
    private String saveCurrentDate, saveCurrentTime;

    FirebaseDatabase firebaseDatabase;
    private FirebaseUser User;
    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;


    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(true);
        progressDialog.setCanceledOnTouchOutside(false);


        sname = (TextView) findViewById(R.id.name);
        add = (TextView) findViewById(R.id.add);
        bgrp = (TextView) findViewById(R.id.bgrp);
        snum = (TextView) findViewById(R.id.snum);

        ImageButton arrowBack = (ImageButton) findViewById(R.id.arrowback_profile);
        arrowBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PostActivity.this, DashboardActivity.class);
                startActivity(intent);

            }
        });

        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
        saveCurrentDate = currentDate.format(calendar.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calendar.getTime());

        bloodGroups = (Spinner) findViewById(R.id.bloodGroups);
        name = (EditText) findViewById(R.id.name);
        address = (EditText) findViewById(R.id.address);
        number = (EditText) findViewById(R.id.number);

        postbtn = (Button) findViewById(R.id.postbtn);
        postbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addlist();
            }
        });
    }

    private void addlist() {
        String userName = name.getText().toString().trim();
        String userAddress = address.getText().toString().trim();
        String userNum = number.getText().toString().trim();
        String userBgrp = bloodGroups.getSelectedItem().toString().trim();

        if (userName.isEmpty()) {
            name.setError("Enter name!");
            Toast.makeText(this, "Please input name...", Toast.LENGTH_SHORT).show();
        } else if (userAddress.isEmpty()) {
            address.setError("Enter address!");
            Toast.makeText(this, "Please input your address...", Toast.LENGTH_SHORT).show();
        } else if (userNum.isEmpty()) {
            number.setError("Enter number!");
            Toast.makeText(this, "Please input your number...", Toast.LENGTH_SHORT).show();
        } else {
            register(userName, userAddress, userBgrp, userNum);
        }

    }

    private void register(String userName, String userAddress, String userBgrp, String userNum) {

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("name", userName);
        hashMap.put("Address", userAddress);
        hashMap.put("bloodGroups", userBgrp);
        hashMap.put("number", userNum);
        hashMap.put("date", saveCurrentDate);
        hashMap.put("time", saveCurrentTime);

        databaseReference = FirebaseDatabase.getInstance().getReference("posts").child(userName);
        databaseReference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    progressDialog.dismiss();
                    Intent intent = new Intent(PostActivity.this, DashboardActivity.class);
                    startActivity(intent);
                    Toast.makeText(PostActivity.this, "Your request has been posted!", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    progressDialog.dismiss();
                    Toast.makeText(PostActivity.this, " " + task.getException(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
