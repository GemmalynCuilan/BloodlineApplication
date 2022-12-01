package com.example.bloodlineapplication.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bloodlineapplication.R;
import com.example.bloodlineapplication.activities.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;


public class RegistrationAdmin extends AppCompatActivity {

    private EditText fullname, email, password;
    private Button signUpButton;
    private FirebaseAuth mAuth;
    private ProgressDialog loadBar;
    private FirebaseUser Admin;
    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_admin);
        fullname = (EditText) findViewById(R.id.fullname);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);

        mAuth = FirebaseAuth.getInstance();
        loadBar = new ProgressDialog(this);
        TextView backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegistrationAdmin.this, AdminLogin.class));
                finish();
            }
        });
        signUpButton = (Button) findViewById(R.id.signUpButton);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PerformAuthentication();
            }
        });
    }

    private void PerformAuthentication() {
        String name = fullname.getText().toString().trim();
        String emailAdd = email.getText().toString().trim();
        String pass = password.getText().toString().trim();

        if (emailAdd.isEmpty()) {
            email.setError("Enter correct E-mail!");
            Toast.makeText(this, "Please input correct E-Mail...", Toast.LENGTH_SHORT).show();
        } else if (pass.isEmpty()) {
            password.setError("Enter Password!");
            Toast.makeText(this, "Please input your password...", Toast.LENGTH_SHORT).show();
        } else if (name.isEmpty()) {
            fullname.setError("Enter your age");
            Toast.makeText(this, "Please enter your age...", Toast.LENGTH_SHORT).show();
        } else {
            register( name, emailAdd, pass);
        }
}

    private void register(String name, String emailAdd, String pass) {
        loadBar.setTitle("Creating Account");
        loadBar.setMessage("Please wait!");
        loadBar.setCanceledOnTouchOutside(false);
        loadBar.show();
        mAuth.createUserWithEmailAndPassword(emailAdd,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Admin = mAuth.getCurrentUser();
                    assert Admin != null;
                    String userId = Admin.getUid();
                    databaseReference = FirebaseDatabase.getInstance().getReference("admin").child(userId);
                    HashMap<String, String> hashMap = new HashMap<>();
                    hashMap.put("userId", userId);
                    hashMap.put("email", emailAdd);
                    hashMap.put("password", pass);
                    hashMap.put("fullname", name);
                    hashMap.put("status", "admin");
                    databaseReference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                loadBar.dismiss();
                                Intent intent = new Intent(RegistrationAdmin.this, AdminLogin.class);
                                startActivity(intent);
                                Toast.makeText(RegistrationAdmin.this, "You are registered sucessfully!", Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                loadBar.dismiss();
                                Toast.makeText(RegistrationAdmin.this, " " + task.getException(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }else{
                    loadBar.dismiss();
                    Toast.makeText(RegistrationAdmin.this, "Account Created Successfull!", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }


}

