package com.example.bloodlineapplication.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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

import java.util.HashMap;
public class RegistrationActivity extends AppCompatActivity{

    private Spinner  bloodGroups, blood;
    private EditText fullname, address, email, password, age;
    private Button signUpButton;
    private FirebaseAuth mAuth;
    private ProgressDialog loadBar;
    private FirebaseUser User;
    private DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        fullname = (EditText) findViewById(R.id.fullname);
        address = (EditText) findViewById(R.id.address);
        email = (EditText) findViewById(R.id.email);
        age = (EditText) findViewById(R.id.age);
        password = (EditText) findViewById(R.id.password);
        bloodGroups = (Spinner) findViewById(R.id.bloodGroups);
        blood = (Spinner) findViewById(R.id.blood);



        mAuth = FirebaseAuth.getInstance();
        loadBar = new ProgressDialog(this);


        TextView backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
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
        String userAddress = address.getText().toString().trim();
        String emailAdd = email.getText().toString().trim();
        String userAge = age.getText().toString().trim();
        String pass = password.getText().toString().trim();
        String bgroup = bloodGroups.getSelectedItem().toString().trim();
        String bloodDr = blood.getSelectedItem().toString().trim();


        if (emailAdd.isEmpty()) {
            email.setError("Enter correct E-mail!");
            Toast.makeText(this, "Please input correct E-Mail...", Toast.LENGTH_SHORT).show();
        } else if (pass.isEmpty()) {
            password.setError("Enter Password!");
            Toast.makeText(this, "Please input your password...", Toast.LENGTH_SHORT).show();
        } else if (userAge.isEmpty()) {
            age.setError("Enter your age");
            Toast.makeText(this, "Please enter your age...", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(userAddress)) {
            address.setError("Enter your address");
            Toast.makeText(this, "Please write your address...", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(name)) {
            fullname.setError("Enter your full name");
            Toast.makeText(this, "Please write your full name...", Toast.LENGTH_SHORT).show();
        } else {
            register(emailAdd, pass, userAge, userAddress, name, bgroup, bloodDr);
        }

    }

    private void register(String emailAdd, String pass, String userAge, String userAddress, String name, String bgroup, String bloodDr) {
        loadBar.setTitle("Creating Account");
        loadBar.setMessage("Please wait!");
        loadBar.setCanceledOnTouchOutside(false);
        loadBar.show();

        mAuth.createUserWithEmailAndPassword(emailAdd,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    User = mAuth.getCurrentUser();
                    assert User != null;
                    String userId = User.getUid();
                    databaseReference = FirebaseDatabase.getInstance().getReference("users").child(userId);
                    HashMap<String, String> hashMap = new HashMap<>();
                    hashMap.put("userId", userId);
                    hashMap.put("email", emailAdd);
                    hashMap.put("password", pass);
                    hashMap.put("age", userAge);
                    hashMap.put("address", userAddress);
                    hashMap.put("fullname", name);
                    hashMap.put("bloodGroups", bgroup);
                    hashMap.put("blood", bloodDr);
                    hashMap.put("profileImage", "default");
                    hashMap.put("status", "user");
                    databaseReference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                loadBar.dismiss();
                                Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                                startActivity(intent);
                                Toast.makeText(RegistrationActivity.this, "User has been registered sucessfully!", Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                loadBar.dismiss();
                                Toast.makeText(RegistrationActivity.this, " " + task.getException(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }else{
                    loadBar.dismiss();
                    Toast.makeText(RegistrationActivity.this, "Account Created Successfull!", Toast.LENGTH_SHORT).show();
                }
                }

        });
    }


    }

