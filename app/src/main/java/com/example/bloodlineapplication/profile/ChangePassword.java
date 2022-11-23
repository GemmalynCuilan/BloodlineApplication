package com.example.bloodlineapplication.profile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.bloodlineapplication.R;
import com.example.bloodlineapplication.activities.DashboardActivity;
import com.example.bloodlineapplication.activities.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class ChangePassword extends AppCompatActivity {
    private EditText oldPasswordSecurity, newPasswordSecurity, confirmNewPasswordSecurity;
    private Button changePass;

    private FirebaseAuth Auth;
    private FirebaseUser Users;

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

        oldPasswordSecurity = (EditText) findViewById(R.id.secuityOldPassword);
        newPasswordSecurity = (EditText) findViewById(R.id.secuityNewPassword);
        confirmNewPasswordSecurity = (EditText) findViewById(R.id.securyConfirmPassword);
        changePass = (Button) findViewById(R.id.securityChangePassBTN);

        Auth = FirebaseAuth.getInstance();
        Users = FirebaseAuth.getInstance().getCurrentUser();

        changePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oldPass = oldPasswordSecurity.getText().toString();
                String newPass = newPasswordSecurity.getText().toString();
                String confirmNewPass = confirmNewPasswordSecurity.getText().toString();
                if(oldPass.isEmpty() || newPass.isEmpty() || confirmNewPass.isEmpty()){
                    Toast.makeText(ChangePassword.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                }else if (newPass.isEmpty()){
                    newPasswordSecurity.setError("Enter your new password");
                    Toast.makeText(ChangePassword.this, "Enter your new password", Toast.LENGTH_SHORT).show();
                }else if (! confirmNewPass.equals(newPass)){
                    confirmNewPasswordSecurity.setError("Password does not match");
                    Toast.makeText(ChangePassword.this, "Password does not match", Toast.LENGTH_SHORT).show();
                }else{
                    changePassword(oldPass,newPass);
                }
            }
        });

    }

    private void changePassword(String oldPass, String newPass) {
        loadBar.setTitle("Changing Password");
        loadBar.setMessage("Loading...");
        loadBar.setCanceledOnTouchOutside(false);
        loadBar.show();
        AuthCredential credential = EmailAuthProvider.getCredential(Users.getEmail(),oldPass);
        Users.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Users.updatePassword(newPass).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                HashMap User = new HashMap();
                                User.put("password", newPass);
                                databaseReference = FirebaseDatabase.getInstance().getReference("users");
                                databaseReference.child(Users.getUid()).updateChildren(User).addOnCompleteListener(new OnCompleteListener() {
                                    @Override
                                    public void onComplete(@NonNull Task task) {
                                        loadBar.dismiss();
                                        Toast.makeText(ChangePassword.this, "Successfully changed, Please Login Again!", Toast.LENGTH_SHORT).show();
                                        Auth.signOut();
                                        Intent intent = new Intent(ChangePassword.this, LoginActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(intent);
                                        finish();
                                    }
                                });
                            }else{
                                loadBar.dismiss();
                                Toast.makeText(ChangePassword.this, "Password should be atleast 6 characters", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }else{
                    loadBar.dismiss();
                    Toast.makeText(ChangePassword.this, "Incorrect Old Password!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
