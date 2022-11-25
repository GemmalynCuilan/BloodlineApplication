package com.example.bloodlineapplication.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bloodlineapplication.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;


public class PostActivity extends AppCompatActivity {
    private ImageButton arrowBack;
    private Spinner bloodGroups;
    private EditText serialNum, address;
    private TextView snum, add, bgrp;
    private Button postbtn;

    FirebaseDatabase firebaseDatabase;
    private FirebaseUser Users;
    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;


    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        bloodGroups = (Spinner) findViewById(R.id.bloodGroups);
        serialNum = (EditText) findViewById(R.id.serialNum);
        address = (EditText) findViewById(R.id.address);
        snum = (TextView) findViewById(R.id.snum);
        add = (TextView) findViewById(R.id.add);
        bgrp = (TextView) findViewById(R.id.bgrp);
        postbtn = (Button) findViewById(R.id.postbtn);



        ImageButton arrowBack = (ImageButton) findViewById(R.id.arrowback_profile);
        arrowBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PostActivity.this, DashboardActivity.class);
                startActivity(intent);

            }
        });

    }
}