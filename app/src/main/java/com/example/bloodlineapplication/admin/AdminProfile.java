package com.example.bloodlineapplication.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.bloodlineapplication.R;
import com.example.bloodlineapplication.activities.DashboardActivity;
import com.example.bloodlineapplication.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdminProfile extends AppCompatActivity {

    private FirebaseUser Admin;
    private FirebaseAuth Auth;
    private DatabaseReference databaseReference;

    private TextView name, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_profile);

        ImageButton arrowBack = (ImageButton) findViewById(R.id.arrowback_profile);
        arrowBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminProfile.this, AdminDashboard.class);
                startActivity(intent);
            }
        });

        name =  (TextView) findViewById(R.id.fullname);
        email =  (TextView) findViewById(R.id.email);


        Auth = FirebaseAuth.getInstance();
        Admin = Auth.getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("admin").child(Admin.getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Admin admin = snapshot.getValue(Admin.class);
                assert admin != null;
                name.setText(admin.getFullname());
                email.setText(admin.getEmail());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(AdminProfile.this,"Error, please report this bug!", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
