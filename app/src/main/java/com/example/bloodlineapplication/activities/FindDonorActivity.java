package com.example.bloodlineapplication.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.bloodlineapplication.R;

public class FindDonorActivity extends AppCompatActivity {
    private ImageButton arrowBack;
    private Button btnSearch;
    private TextView textView2;
    private Spinner bloodGroups;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_donor);

        bloodGroups = (Spinner) findViewById(R.id.bloodGroups);
        btnSearch = (Button) findViewById(R.id.btnSearch);
        textView2 = (TextView) findViewById(R.id.textView2);


        ImageButton arrowBack = (ImageButton) findViewById(R.id.arrowback_profile);
        arrowBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FindDonorActivity.this, DashboardActivity.class);
                startActivity(intent);
            }
        });
    }
}