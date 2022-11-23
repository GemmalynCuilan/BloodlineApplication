package com.example.bloodlineapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.bloodlineapplication.R;
import com.example.bloodlineapplication.profile.ChangePassword;
import com.example.bloodlineapplication.profile.ChangeProfile;
import com.example.bloodlineapplication.profile.MyProfile;
import com.example.bloodlineapplication.profile.Profile;
import com.example.bloodlineapplication.update.User;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import de.hdodenhof.circleimageview.CircleImageView;
public class DashboardActivity extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    private FirebaseUser User;
    private FirebaseAuth Auth;
    private RecyclerView myList;
    private TextView menu_profile, menu_search, menu_view, menu_banks;
    private Button logout;

    private String userId = "";
    private FirebaseUser user;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        menu_profile = (TextView) findViewById(R.id.menu_profile);
        menu_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, MyProfile.class);
                startActivity(intent);
            }
        });
        menu_search = (TextView) findViewById(R.id.menu_search);
        menu_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, FindDonorActivity.class);
                startActivity(intent);
            }
        });
        menu_view = (TextView) findViewById(R.id.menu_view);
        menu_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, FindDonorActivity.class);
                startActivity(intent);
            }
        });
        menu_banks = (TextView) findViewById(R.id.menu_banks);
        menu_banks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, MapActivity.class);
                startActivity(intent);
            }
        });
        //myList = (RecyclerView) findViewById(R.id.recycler_menu);
        //myList.setLayoutManager(new LinearLayoutManager(DashboardActivity.this));

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
        CircleImageView profileImageView = headerView.findViewById(R.id.profileImage);

        Auth = FirebaseAuth.getInstance();
        User = Auth.getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("users").child(User.getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                assert user != null;
                name.setText(user.fullname);
                name.setAllCaps(true);
                if(user.getProfileImage().equals("default")){
                    profileImageView.setImageResource(R.drawable.logo);
                }else{
                    Glide.with(getApplicationContext()).load(user.getProfileImage()).into(profileImageView);
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(DashboardActivity.this, "Error", Toast.LENGTH_SHORT).show();

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

        if (id == R.id.nav_changeProfile) {
            Intent intent = new Intent(DashboardActivity.this, ChangeProfile.class);
            startActivity(intent);
        }
        if (id == R.id.nav_changePassword) {
            Intent intent = new Intent(DashboardActivity.this, ChangePassword.class);
            startActivity(intent);
        }
        if (id == R.id.nav_messages) {
            Intent intent = new Intent(DashboardActivity.this, MapActivity.class);
            startActivity(intent);

        }else if (id == R.id.menuLogout) {
            Intent intent = new Intent(DashboardActivity.this, LoginActivity.class);
            Toast.makeText(DashboardActivity.this,"User has been Logout sucessfully!",Toast.LENGTH_LONG).show();
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            Auth.signOut();
            startActivity(intent);
            finish();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;


    }
/*
    @Override
    protected void onStart() {
        super.onStart();
        reference = FirebaseDatabase.getInstance().getReference().child("users");
        FirebaseRecyclerOptions<User> options = new FirebaseRecyclerOptions.Builder<User>()
                .setQuery(reference.orderByChild("name").equalTo("Blood Donor"), User.class).build();
        FirebaseRecyclerAdapter<User, UserViewHolder> adapter =
                new FirebaseRecyclerAdapter<User, UserViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull UserViewHolder holder, int position, @NonNull User user) {
                        holder.fullname.setText(user.getName());
                        holder.address.setText(user.getUserAddress());
                        holder.email.setText(user.getEmailAdd());
                        holder.bloodGroups.setText(user.getBgroup());
                        holder.blood.setText(user.getBloodDr());
                    }
                    @NonNull
                    @Override
                    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_layout, parent, false);
                        UserViewHolder userHolder = new UserViewHolder(view);
                        return userHolder;
                    }
                };
        recyclerView.setAdapter(adapter);
        adapter.startListening();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);
*/
}