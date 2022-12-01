package com.example.bloodlineapplication.profile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.bloodlineapplication.R;
import com.example.bloodlineapplication.activities.DashboardActivity;
import com.example.bloodlineapplication.activities.LoginActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

public class ChangePassword extends AppCompatActivity {


    private EditText oldPassword, newPassword, confirmPassword;
    private Button changePassBTN;

    private FirebaseAuth Auth;
    private FirebaseUser User;

    private ProgressDialog loadBar;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        ImageButton arrowBack = (ImageButton) findViewById(R.id.arrowback_profile);
        arrowBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChangePassword.this, DashboardActivity.class);
                startActivity(intent);
            }
        });

        loadBar = new ProgressDialog(this);

        oldPassword = (EditText) findViewById(R.id.oldPassword);
        newPassword = (EditText) findViewById(R.id.newPassword);
        //confirmPassword = (EditText) findViewById(R.id.confirmPassword);
        changePassBTN = (Button) findViewById(R.id.changePassBTN);

        Auth = FirebaseAuth.getInstance();
        User = FirebaseAuth.getInstance().getCurrentUser();

        changePassBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String oldPass = oldPassword.getText().toString().trim();
                String newPass = newPassword.getText().toString().trim();
                //String conPass = confirmPassword.getText().toString().trim();
                if (TextUtils.isEmpty(oldPass)){
                    Toast.makeText(ChangePassword.this, "Enter your current password..", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (newPass.length()<6){
                    Toast.makeText(ChangePassword.this, "Password length must atleast 6 characters..", Toast.LENGTH_SHORT).show();
                    return;
                }
                updatePassword(oldPass, newPass);
            }
        });

    }

    private void updatePassword(String oldPass, String newPass) {
        Auth = FirebaseAuth.getInstance();
        User = Auth.getCurrentUser();
        AuthCredential authCredential = EmailAuthProvider.getCredential(User.getEmail(),oldPass);
        User.reauthenticate(authCredential).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                User.updatePassword(newPass).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(ChangePassword.this, "Password successfully updated", Toast.LENGTH_SHORT).show();
                        Auth.signOut();
                        Intent intent = new Intent(ChangePassword.this, LoginActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(ChangePassword.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
                }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(ChangePassword.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                }

        });
    }
}