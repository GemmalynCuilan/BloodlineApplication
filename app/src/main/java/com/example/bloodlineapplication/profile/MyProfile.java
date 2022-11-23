package com.example.bloodlineapplication.profile;

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
import com.example.bloodlineapplication.update.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyProfile extends AppCompatActivity {

    private FirebaseUser Users;
    private FirebaseAuth Auth;
    private DatabaseReference databaseReference;

    private TextView name, phone, address, email, bloodDr, bgroup;
    private CircleImageView profileImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        ImageButton arrowBack = (ImageButton) findViewById(R.id.arrowback_profile);
        arrowBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyProfile.this, DashboardActivity.class);
                startActivity(intent);
            }
        });

        name =  (TextView) findViewById(R.id.fullname);
        address =  (TextView) findViewById(R.id.address);
        email =  (TextView) findViewById(R.id.email);
        phone =  (TextView) findViewById(R.id.phoneNumber);
        bgroup = (TextView) findViewById(R.id.bloodGroups);
        bloodDr = (TextView) findViewById(R.id.blood);


        profileImage = (CircleImageView) findViewById(R.id.profileImage);

        Auth = FirebaseAuth.getInstance();
        Users = Auth.getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("users").child(Users.getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                assert user != null;
                bloodDr.setText(user.getBlood());
                bloodDr.setAllCaps(true);
                name.setText(user.getFullname());
                phone.setText(user.getPhoneNumber());
                address.setText(user.getHouseAddress());
                email.setText(Users.getEmail());
                bgroup.setText(user.getBloodGroups());
                bgroup.setAllCaps(true);


                if(user.getProfileImage().equals("default")){
                    profileImage.setImageResource(R.drawable.logo);
                }else{
                    Glide.with(getApplicationContext()).load(user.getProfileImage()).into(profileImage);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MyProfile.this,"Error, please report this bug!", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
