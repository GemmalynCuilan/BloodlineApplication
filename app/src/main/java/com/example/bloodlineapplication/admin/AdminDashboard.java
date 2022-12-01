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

import com.bumptech.glide.Glide;
import com.example.bloodlineapplication.R;
import com.example.bloodlineapplication.activities.DashboardActivity;
import com.example.bloodlineapplication.activities.FindDonorActivity;
import com.example.bloodlineapplication.activities.LoginActivity;
import com.example.bloodlineapplication.model.User;
import com.example.bloodlineapplication.profile.ChangePassword;
import com.example.bloodlineapplication.profile.ChangeProfile;
import com.example.bloodlineapplication.profile.MyProfile;
import com.google.android.gms.auth.api.Auth;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdminDashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    private FirebaseUser Admin;
    private FirebaseAuth Auth;
    private Button logout;

    private ImageView menu_profile, menu_request;

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
        int id = item.getItemId();


        if (id == R.id.nav_changePassword) {
            Intent intent = new Intent(AdminDashboard.this, ChangePassword.class);
            startActivity(intent);

        }
        if (id == R.id.nav_aP) {
            Intent intent = new Intent(AdminDashboard.this, ChangePassword.class);
            startActivity(intent);

        }
        if (id == R.id.nav_aN) {
            Intent intent = new Intent(AdminDashboard.this, ChangePassword.class);
            startActivity(intent);

        }
        if (id == R.id.nav_bP) {
            Intent intent = new Intent(AdminDashboard.this, ChangePassword.class);
            startActivity(intent);

        }
        if (id == R.id.nav_bN) {
            Intent intent = new Intent(AdminDashboard.this, ChangePassword.class);
            startActivity(intent);

        }
        if (id == R.id.nav_abP) {
            Intent intent = new Intent(AdminDashboard.this, ChangePassword.class);
            startActivity(intent);

        }
        if (id == R.id.nav_abN) {
            Intent intent = new Intent(AdminDashboard.this, ChangePassword.class);
            startActivity(intent);

        }
        if (id == R.id.nav_oP) {
            Intent intent = new Intent(AdminDashboard.this, ChangePassword.class);
            startActivity(intent);

        }
        if (id == R.id.nav_oN) {
            Intent intent = new Intent(AdminDashboard.this, ChangePassword.class);
            startActivity(intent);

        } else if (id == R.id.menuLogout) {
            Intent intent = new Intent(AdminDashboard.this, LoginActivity.class);
            Toast.makeText(AdminDashboard.this, "User has been Logout sucessfully!", Toast.LENGTH_LONG).show();
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            Auth.signOut();
            startActivity(intent);
            finish();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;


    }
}