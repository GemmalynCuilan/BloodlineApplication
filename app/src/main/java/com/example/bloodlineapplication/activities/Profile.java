package com.example.bloodlineapplication.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bloodlineapplication.R;
import com.example.bloodlineapplication.update.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Profile extends AppCompatActivity{

    private Button logout;

    private FirebaseUser user;
    private DatabaseReference reference;

    private String userID;

    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_profile);

        logout = (Button) findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(Profile.this, MainActivity.class));
            }
        });

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("users");
        userID = user.getUid();

        final TextView fullnametitle = (TextView) findViewById(R.id.fullname);
        final TextView addresstitle = (TextView) findViewById(R.id.address);
        final TextView emailtitle = (TextView) findViewById(R.id.email);
        final TextView bloodGrouptitle = (TextView)findViewById(R.id.bloodGroups);
        final TextView bloodtitle = (TextView) findViewById(R.id.blood);

        reference.child(userID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue (User.class);
                if (user != null) {
                    String fullname = user.name;
                    String userAddress = user.userAddress;
                    String emailAddress = user.emailAdd;
                    String pass = user.pass;
                    String bgroup = user.bgroup;
                    String bloodDr = user.bloodDr;

                    fullnametitle.setText(fullname);
                    addresstitle.setText(userAddress);
                    emailtitle.setText(emailAddress);
                    bloodGrouptitle.setText(bgroup);
                    bloodtitle.setText(bloodDr);
                }

            }
                @Override
            public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(Profile.this, "Something wrong happened!", Toast.LENGTH_LONG).show();
            }
        });


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
        HomeActivity.redirectActivity(this, MyProfile.class);
    }
    public void ClickProfile(View view){
        recreate();

    }
    @Override
    protected void onPause() {
        super.onPause();
        //close drawer
        HomeActivity.closeDrawer(drawerLayout);
    }

}
