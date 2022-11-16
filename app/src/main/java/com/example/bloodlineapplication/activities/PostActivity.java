package com.example.bloodlineapplication.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.location.Address;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.bloodlineapplication.R;
import com.example.bloodlineapplication.update.UserData;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class PostActivity extends AppCompatActivity {

    ProgressDialog pd;

    EditText address, email;
    Spinner bloodGroups, blood;
    Button btnpost;

    FirebaseDatabase fdb;
    DatabaseReference db_ref;
    FirebaseAuth mAuth;

    Calendar cal;
    String uid;
    String Time, Date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);


        getSupportActionBar().setTitle("Post Blood Request");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        email = findViewById(R.id.email);
        address = findViewById(R.id.address);

        bloodGroups = findViewById(R.id.bloodGroups);
        blood = findViewById(R.id.blood);

        btnpost = findViewById(R.id.postbtn);



        FirebaseUser cur_user = mAuth.getInstance().getCurrentUser();

        if(cur_user == null)
        {
            startActivity(new Intent(PostActivity.this, LoginActivity.class));
        } else {
            uid = ((FirebaseUser) cur_user).getUid();
        }

        mAuth = FirebaseAuth.getInstance();
        fdb = FirebaseDatabase.getInstance();
        db_ref = fdb.getReference("posts");

        try {
            btnpost.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pd.show();
                    final Query findname = fdb.getReference("users").child(uid);

                    if(email.getText().length() == 0)
                    {
                        Toast.makeText(getApplicationContext(), "Enter your contact number!",
                                Toast.LENGTH_LONG).show();
                    }
                    else if(address.getText().length() == 0)
                    {
                        Toast.makeText(getApplicationContext(), "Enter your location!",
                                Toast.LENGTH_LONG).show();
                    }
                    else {
                        findname.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                if (dataSnapshot.exists()) {
                                    db_ref.child(uid).child("Name").setValue(dataSnapshot.getValue(UserData.class).getName());
                                    db_ref.child(uid).child("Email").setValue(email.getText().toString());
                                    db_ref.child(uid).child("Address").setValue(address.getText().toString());
                                    db_ref.child(uid).child("Division").setValue(blood.getSelectedItem().toString());
                                    db_ref.child(uid).child("BloodGroup").setValue(bloodGroups.getSelectedItem().toString());
                                   // db_ref.child(uid).child("Time").setValue(Time);
                                    //db_ref.child(uid).child("Date").setValue(Date);
                                    Toast.makeText(PostActivity.this, "Your post has been created successfully",
                                            Toast.LENGTH_LONG).show();
                                    startActivity(new Intent(PostActivity.this, HomeActivity.class));

                                } else {
                                    Toast.makeText(getApplicationContext(), "Database error occured.",
                                            Toast.LENGTH_LONG).show();
                                }

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                                Log.d("User", databaseError.getMessage());

                            }
                        });
                    }
                }
            });
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        pd.dismiss();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}