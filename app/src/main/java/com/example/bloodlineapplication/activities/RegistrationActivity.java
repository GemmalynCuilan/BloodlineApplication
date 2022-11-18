package com.example.bloodlineapplication.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bloodlineapplication.R;
import com.example.bloodlineapplication.update.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener{

    private Spinner  bloodGroups, blood;
    private EditText fullname, address, email, password, phoneNumber;
    private Button signUpButton;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        fullname = (EditText) findViewById(R.id.fullname);
        address = (EditText) findViewById(R.id.address);
        email = (EditText) findViewById(R.id.email);
        phoneNumber = (EditText) findViewById(R.id.phoneNumber);
        password = (EditText) findViewById(R.id.password);
        bloodGroups = (Spinner) findViewById(R.id.bloodGroups);
        blood = (Spinner) findViewById(R.id.blood);

        mAuth = FirebaseAuth.getInstance();

        signUpButton = (Button) findViewById(R.id.signUpButton);
        signUpButton.setOnClickListener(this);
        TextView backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
                finish();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.signUpButton:
                signUpButton();
                break;
            case R.id.backButton:
                startActivity(new Intent(this, LoginActivity.class));

        }
    }

    private void signUpButton() {
        String name = fullname.getText().toString().trim();
        String userAddress = address.getText().toString().trim();
        String emailAdd = email.getText().toString().trim();
        String phone = phoneNumber.getText().toString().trim();
        String pass = password.getText().toString().trim();
        String bgroup = bloodGroups.getSelectedItem().toString().trim();
        String bloodDr = blood.getSelectedItem().toString().trim();

        if(name.isEmpty()){
            fullname.setError("Full name is required");
            fullname.requestFocus();
            return;
        }
        if(userAddress.isEmpty()){
            address.setError("Address is required");
            address.requestFocus();
            return;
        }
        if(emailAdd.isEmpty()){
            email.setError("Email Address is required");
            email.requestFocus();
            return;
        }
        if(phone.isEmpty()){
            phoneNumber.setError("Phone number is required");
            phoneNumber.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(emailAdd).matches()){
            email.setError("Please provide valid email");
            email.requestFocus();
            return;
        }
        if(pass.isEmpty()){
            password.setError("Password is required");
            password.requestFocus();
            return;
        }
        if(pass.length()<6){
            password.setError("Min password length should be 6 characters");
            password.requestFocus();
        }
        if(bgroup.isEmpty()){
            bloodGroups.requestFocus();
            return;
        }
        if(bloodDr.isEmpty()){
            blood.requestFocus();
            return;
        }
        mAuth.createUserWithEmailAndPassword(emailAdd,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    User user = new User(name, userAddress, emailAdd, phone ,bgroup, bloodDr);
                    FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance()
                            .getCurrentUser().getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(RegistrationActivity.this,"User has been registered sucessfully!",Toast.LENGTH_LONG).show();
                                startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
                                finish();

                            }else{
                                Toast.makeText(RegistrationActivity.this, "Failed to register!Try again!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
}
