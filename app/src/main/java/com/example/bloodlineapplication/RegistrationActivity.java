package com.example.bloodlineapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegistrationActivity extends AppCompatActivity {
   CheckBox bloodDonor, bloodRecipient;
   Spinner blood, bloodGroup;


    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://bloodlineapplication-default-rtdb.firebaseio.com/");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        final EditText username = findViewById(R.id.username);
        final EditText address = findViewById(R.id.address);
        final EditText email = findViewById(R.id.email);
        final EditText password = findViewById(R.id.password);
        final Spinner bloodGroup = findViewById(R.id.bloodGroup);
        final Spinner blood = findViewById(R.id.blood);


        final Button signUpButton = findViewById(R.id.signUpButton);
        final TextView backButton = findViewById(R.id.backButton);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String name = username.getText().toString();
                final String userAddress = address.getText().toString();
                final String emailAddress = email.getText().toString();
                final String pass = password.getText().toString();
                final String bgroup = bloodGroup.getSelectedItem().toString();
                final String bloodDR = blood.getSelectedItem().toString();


                if(name.isEmpty() || userAddress.isEmpty() || emailAddress.isEmpty()|| pass.isEmpty()|| bgroup.isEmpty()){
                    Toast.makeText(RegistrationActivity.this, "Please fill fields", Toast.LENGTH_SHORT).show();
                }
                else{
                    databaseReference.child("users").child(name).child("address").setValue(userAddress);
                    databaseReference.child("users").child(name).child("email").setValue(emailAddress);
                    databaseReference.child("users").child(name).child("bloodGroup").setValue(bgroup);
                    databaseReference.child("users").child(name).child("password").setValue(pass);
                    databaseReference.child("users").child(name).child("blood").setValue(bloodDR);

                    Toast.makeText(RegistrationActivity.this,"User registered Succesfully", Toast.LENGTH_SHORT).show();
                    finish();
                }

            }
        });


        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
                finish();
            }
        });
    }
}