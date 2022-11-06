package com.example.bloodlineapplication;

import static com.example.bloodlineapplication.HomeActivity.redirectActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

public class FindCenter extends AppCompatActivity {
    DrawerLayout drawerLayout;

    Spinner spType;
    Button findBtn;
    SupportMapFragment supportMapFragment;
    GoogleMap map;
    FusedLocationProviderClient fusedLocationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_blood_donor);

        drawerLayout = findViewById(R.id.drawer_layout);
        //Assign variables
      //  spType = findViewById(R.id.sp_type);
       // findBtn = findViewById(R.id.findBtn);
        //supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.googleMap);
        //initialize array of place type
        /*[] placeTypeList = {"bloodBanks", "hospital"};
        //initialize array of place name
        String[] placeNameList = {"Blood Banks", "Hospital"};
        //set adapter on spinner
        spType.setAdapter(new ArrayAdapter<>(FindCenter.this, android.R.layout.simple_spinner_dropdown_item,
                placeNameList));
        //initialize the fused location provider client
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        //check permission
        if (ActivityCompat.checkSelfPermission(FindCenter.this, Manifest.permission.
                ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            //permission granted
            //call method
            getCurrentLocation();
        }
    }

    private void getCurrentLocation() {
        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if(location != null){
                    currentLat = location.getLatitude()
                }
            }
        }) ;

         */
    }


    public void ClickMenu(View view){
                HomeActivity.openDrawer(drawerLayout);
            }
            public void ClickLogo(View view){
                HomeActivity.closeDrawer(drawerLayout);
            }
            public void ClickHome(View view){
                redirectActivity(this, HomeActivity.class);
            }
            public void ClickFindBloodDonor(View view){

                redirectActivity(this, FindBloodDonor.class);
            }
            public void ClickProfile(View view){
                redirectActivity(this, Profile.class);
            }
            public void ClickFindCenter(View view){
                recreate();
            }
            public void ClickLogout(View view){
                HomeActivity.logout(this);
            }

            @Override
            protected void onPause() {
                super.onPause();
                //close drawer
                HomeActivity.closeDrawer(drawerLayout);
            }
        }