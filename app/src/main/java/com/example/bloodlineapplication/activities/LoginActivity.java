package com.example.bloodlineapplication.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bloodlineapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView backButton, loginButton;
    private EditText email, password;

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(this);

        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.backButton:
                startActivity(new Intent(this, RegistrationActivity.class));
                break;
            case R.id.loginButton:
                userLogin();
                break;
    }
}

    private void userLogin() {
        String emailAdd = email.getText().toString().trim();
        String  pass = password.getText().toString().trim();
        if(emailAdd.isEmpty()){
            email.setError("Email is required!");
            email.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(emailAdd).matches()){
            email.setError("Please enter a valid email!");
            email.requestFocus();
            return;
        }
        if(pass.isEmpty()){
            password.setError("Password id required!");
            password.requestFocus();
        }
        if(pass.length() < 6){
            password.setError("Min password length is 6 characters!");
            password.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(emailAdd, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    Toast.makeText(LoginActivity.this, "Successfully logged in", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this, DashboardActivity.class));
                    finish();

                }else{
                    Toast.makeText(LoginActivity.this, "Failed to login!Please check your credentials", Toast.LENGTH_LONG).show();

                }
            }
        });
    }
    }