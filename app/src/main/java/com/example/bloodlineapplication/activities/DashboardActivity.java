package com.example.bloodlineapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bloodlineapplication.R;
import com.example.bloodlineapplication.adapters.UserAdapter;
import com.example.bloodlineapplication.databinding.ActivityDashboardBinding;
import com.example.bloodlineapplication.fragment.HomeView;
import com.example.bloodlineapplication.update.User;
import com.google.android.gms.auth.api.Auth;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class DashboardActivity extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    private FirebaseUser User;
    private FirebaseAuth Auth;
    private RecyclerView myList;

    private Button logout;


    private FirebaseUser user;
    private DatabaseReference reference;

    private String userID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);


        myList = (RecyclerView) findViewById(R.id.recycler_menu);
        myList.setLayoutManager(new LinearLayoutManager(DashboardActivity.this));

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("BloodLine");
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DashboardActivity.this, PostActivity.class));
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View headerView = navigationView.getHeaderView(0);
        TextView name = (TextView) headerView.findViewById(R.id.profilename);
        TextView bloodDr = (TextView) headerView.findViewById(R.id.profileblood);
        TextView bgroup = (TextView) headerView.findViewById(R.id.profilebloodtype);
        Auth = FirebaseAuth.getInstance();
        User = Auth.getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("users").child(User.getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                assert user != null;
                name.setText(user.getName());
                name.setAllCaps(true);
                bloodDr.setText(user.getBloodDr());
                bloodDr.setAllCaps(false);
                bgroup.setText(user.getBgroup());
                bgroup.setAllCaps(true);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(DashboardActivity.this,"Error", Toast.LENGTH_SHORT).show();

            }
        });

        recyclerView = findViewById(R.id.recycler_menu);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
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
        if (id == R.id.nav_profile) {
            Intent intent = new Intent(DashboardActivity.this, MyProfile.class);
            startActivity(intent);
        }

        if (id == R.id.nav_recipient) {
            Intent intent = new Intent(DashboardActivity.this, MyProfile.class);
            startActivity(intent);
        }
        if (id == R.id.nav_bdonor) {
            Intent intent = new Intent(DashboardActivity.this, FindDonorActivity.class);
            startActivity(intent);
        }
        if (id == R.id.nav_banks) {
            Intent intent = new Intent(DashboardActivity.this, MapActivity.class);
            startActivity(intent);

        }/*else if (id == R.id.menuLogout) {
        Intent intent = new Intent(DashboardActivity.this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        Auth.signOut();
        startActivity(intent);
        finish();
    }*/
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;


    }

}