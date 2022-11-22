package com.example.bloodlineapplication.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bloodlineapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity  {

    private TextView backButton, loginButton;
    private EditText email, password;
    ProgressDialog loadBar;
    private FirebaseAuth mAuth;
    FirebaseUser User;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        backButton = (TextView) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });
        mAuth = FirebaseAuth.getInstance();
        User = mAuth.getCurrentUser();
        loadBar = new ProgressDialog(this);

        loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }
        });
    }

    private void loginUser() {
        String emailAdd = email.getText().toString().trim();
        String  pass = password.getText().toString().trim();
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference().child("users");

        if (emailAdd.isEmpty()) {
            email.setError("Enter correct E-mail!");
        } else if (pass.isEmpty()) {
            password.setError("Enter correct Password!");
        }else{
            loadBar.setTitle("Logging In...");
            loadBar.setCanceledOnTouchOutside(false);
            loadBar.show();

            mAuth.signInWithEmailAndPassword(emailAdd,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        User = FirebaseAuth.getInstance().getCurrentUser();
                        Toast.makeText(LoginActivity.this, "Successfully logged in", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this, DashboardActivity.class));
                        finish();

                    }else {
                        loadBar.dismiss();
                        Toast.makeText(LoginActivity.this, " " + task.getException(), Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }

    }
}