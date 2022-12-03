package com.example.bloodlineapplication.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bloodlineapplication.R;
import com.example.bloodlineapplication.profile.ChangePassword;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AdminDashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    private FirebaseUser Admin;
    private FirebaseAuth Auth;
    private Button logout;

    private ImageView menu_profile, menu_request, menu_message;

    private DatabaseReference databaseReference;
    private String userId = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        menu_profile = (ImageView) findViewById(R.id.menu_profile);
        menu_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminDashboard.this, MainActivity2.class);
                startActivity(intent);
            }
        });
        menu_request = (ImageView) findViewById(R.id.menu_request);
        menu_request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminDashboard.this, ViewRequestActivity.class);
                startActivity(intent);
            }
        });
        menu_message = (ImageView) findViewById(R.id.menu_message);
        menu_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminDashboard.this, ViewRequestActivity.class);
                startActivity(intent);
            }
        });


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("BloodLine");
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View headerView = navigationView.getHeaderView(0);
        TextView name = (TextView) headerView.findViewById(R.id.profilename);
        //CircleImageView profileImageView = headerView.findViewById(R.id.profileImage);

        Auth = FirebaseAuth.getInstance();
        Admin = Auth.getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("admin").child(Admin.getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Admin admin = snapshot.getValue(Admin.class);
                assert admin != null;
                name.setText(admin.fullname);
                name.setAllCaps(true);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(AdminDashboard.this, "Error", Toast.LENGTH_SHORT).show();

            }
        });

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){

            case R.id.nav_ap:
                Intent intent1 = new Intent(AdminDashboard.this, CategorySelectedItem.class);
                startActivity(intent1);
                break;
            case R.id.nav_an:
                Intent intent2 = new Intent(AdminDashboard.this, CategorySelectedItem.class);
                startActivity(intent2);
                break;
            case R.id.nav_changePassword:
            Intent intent3 = new Intent(AdminDashboard.this, ChangePassword.class);
            startActivity(intent3);
            break;

            case R.id.menuLogout:
            Intent intent4 = new Intent(AdminDashboard.this, AdminLogin.class);
            Toast.makeText(AdminDashboard.this, "User has been Logout sucessfully!", Toast.LENGTH_LONG).show();
            intent4.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            Auth.signOut();
            startActivity(intent4);
            finish();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;


    }
}