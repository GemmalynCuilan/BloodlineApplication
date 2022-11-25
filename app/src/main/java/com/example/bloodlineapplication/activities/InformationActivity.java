package com.example.bloodlineapplication.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.bloodlineapplication.R;

import org.w3c.dom.Text;

public class InformationActivity extends AppCompatActivity {
    private ImageButton arrowBack;
    private ImageView image;
    private TextView textView, hotline, mobileNum, teleNum, conNum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_information);

        textView = (TextView) findViewById(R.id.textView);
        hotline = (TextView) findViewById(R.id.hotline);
        mobileNum = (TextView) findViewById(R.id.mobileNum);
        teleNum = (TextView) findViewById(R.id.teleNum);
        conNum = (TextView) findViewById(R.id.conNum);
        image = (ImageView) findViewById(R.id.image);


        ImageButton arrowBack = (ImageButton) findViewById(R.id.arrowback_profile);
        arrowBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InformationActivity.this, DashboardActivity.class);
                startActivity(intent);
            }
        });
    }
}