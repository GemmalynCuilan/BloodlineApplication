package com.example.bloodlineapplication.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bloodlineapplication.R;
import com.example.bloodlineapplication.databinding.ActivityMainBinding;
import com.example.bloodlineapplication.model.UserData;
import com.example.bloodlineapplication.update.CustomUserData;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;


public class PostActivity extends AppCompatActivity {


    private ImageButton arrowBack;
    private Spinner bloodGroups;
    private EditText serialnumber, address;
    private TextView snum, add, bgrp;
    private Button postbtn;


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


        snum = (TextView) findViewById(R.id.snum);
        add = (TextView) findViewById(R.id.add);
        bgrp = (TextView) findViewById(R.id.bgrp);

        ImageButton arrowBack = (ImageButton) findViewById(R.id.arrowback_profile);
        arrowBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PostActivity.this, DashboardActivity.class);
                startActivity(intent);

            }
        });

        bloodGroups = (Spinner) findViewById(R.id.bloodGroups);
        serialnumber = (EditText) findViewById(R.id.serialNum);
        address = (EditText) findViewById(R.id.address);

        postbtn = (Button) findViewById(R.id.postbtn);
        postbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addlist();
            }
        });
    }

    private void addlist() {
        String snumber = serialnumber.getText().toString().trim();
        String userAddress = address.getText().toString().trim();
        String userBgrp = bloodGroups.getSelectedItem().toString().trim();

        if (snumber.isEmpty()) {
            serialnumber.setError("Enter correct Serial number!");
            Toast.makeText(this, "Please input correct serial number...", Toast.LENGTH_SHORT).show();
        } else if (userAddress.isEmpty()) {
            address.setError("Enter address!");
            Toast.makeText(this, "Please input your address...", Toast.LENGTH_SHORT).show();
        } else {
            register(snumber, userAddress, userBgrp);
        }

    }

    private void register(String snumber, String userAddress, String userBgrp) {

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("Serial number", snumber);
        hashMap.put("Address", userAddress);
        hashMap.put("Blood Group", userBgrp);

        databaseReference = FirebaseDatabase.getInstance().getReference("posts").child(snumber);
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
