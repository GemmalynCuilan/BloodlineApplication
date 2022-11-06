package com.example.bloodlineapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.View;

public class Profile extends AppCompatActivity {
    DrawerLayout drawerLayout;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

    drawerLayout = findViewById(R.id.drawer_layout);
    }
    public void ClickMenu(View view){
        HomeActivity.openDrawer(drawerLayout);
    }
    public void ClickLogo(View view){
        HomeActivity.closeDrawer(drawerLayout);
    }
    public void ClickHome(View view){
        HomeActivity.redirectActivity(this, HomeActivity.class);
    }
    public void ClickFindBloodDonor(View view){
        HomeActivity.redirectActivity(this, FindBloodDonor.class);
    }
    public void ClickProfile(View view){
        recreate();

    }
    public void ClickFindCenter(View view){
        HomeActivity.redirectActivity(this, FindCenter.class);
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
