package com.example.bloodlineapplication;

import static com.example.bloodlineapplication.HomeActivity.redirectActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.View;

public class FindBloodDonor extends AppCompatActivity {

    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_blood_donor);

        drawerLayout = findViewById(R.id.drawer_layout);
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
        recreate();
    }
    public void ClickProfile(View view){
        redirectActivity(this, Profile.class);
    }
    public void ClickFindCenter(View view){
        redirectActivity(this, FindCenter.class);
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