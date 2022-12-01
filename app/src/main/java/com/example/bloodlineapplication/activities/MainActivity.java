package com.example.bloodlineapplication.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.bloodlineapplication.R;
import com.example.bloodlineapplication.activities.LoginActivity;
import com.example.bloodlineapplication.activities.RegistrationActivity;
import com.example.bloodlineapplication.admin.AdminLogin;
import com.example.bloodlineapplication.admin.RegistrationAdmin;

public class MainActivity extends AppCompatActivity {


    private Button admin;
    private Button user;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        admin = (Button) findViewById(R.id.admin);
        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openadmin();
            }
        });

        user = (Button) findViewById(R.id.user);
        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openuser();
            }
        });

    }

    public void openadmin() {
        Intent intent = new Intent(getApplicationContext(), RegistrationAdmin.class);
        startActivity(intent);

    }

    public void openuser() {
        Intent intent = new Intent(getApplicationContext(), RegistrationActivity.class);
        startActivity(intent);

    }


}