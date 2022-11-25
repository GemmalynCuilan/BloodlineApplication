package com.example.bloodlineapplication.profile;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.bloodlineapplication.R;
import com.example.bloodlineapplication.activities.DashboardActivity;
import com.example.bloodlineapplication.update.User;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.squareup.picasso.Picasso;


import java.util.HashMap;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChangeProfile extends AppCompatActivity {

    FirebaseAuth Auth;
    FirebaseUser Users;

    private CircleImageView profileImage;
    private EditText address, phoneNumber, fullname, emailAdd;
    private Button updateBTN;
    private Button changeProfilePic;
    private DatabaseReference databaseReference;
    private Uri imageUri;
    private String myUrl = "";
    private StorageTask uploadTask;
    private StorageReference storageProfilePrictureRef;
    private String checker = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_profile);

        storageProfilePrictureRef = FirebaseStorage.getInstance().getReference().child("Profile pictures");

        ImageButton arrowBack = (ImageButton) findViewById(R.id.arrowback_profile);
        arrowBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChangeProfile.this, DashboardActivity.class);
                startActivity(intent);
            }
        });

        profileImage = (CircleImageView) findViewById(R.id.profileEdit);
        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checker = "clicked";
                //CropImage.activity(imageUri).setAspectRatio(1, 1).start(ChangeProfile.this);
            }
        });

        updateBTN = (Button) findViewById(R.id.saveButton);
        updateBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (checker.equals("clicked")) {
                    updateWithProfilePic();
                } else {
                    updateWithNoProfilePic();
                }
            }
        });

        fullname = (EditText) findViewById(R.id.fullnameEdit);
        address = (EditText) findViewById(R.id.addressEdit);
        phoneNumber = (EditText) findViewById(R.id.phoneNumberEdit);
        emailAdd = (EditText) findViewById(R.id.emailEdit);

        Auth = FirebaseAuth.getInstance();
        Users = Auth.getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("users").child(Users.getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                assert user != null;

                fullname.setText(user.getFullname());
                phoneNumber.setText(user.getPhoneNumber());
                address.setText(user.getHouseAddress());
                emailAdd.setText(user.getEmail());


                if (user.getProfileImage().equals("default")) {
                    profileImage.setImageResource(R.drawable.logo);
                } else {
                    Glide.with(getApplicationContext()).load(user.getProfileImage()).into(profileImage);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ChangeProfile.this, "Error, please report this bug!", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void updateWithProfilePic() {
        String name = fullname.getText().toString();
        String add = address.getText().toString();
        String phone = phoneNumber.getText().toString();
        String emailAdds = emailAdd.getText().toString();


        if (TextUtils.isEmpty(name)) {
            fullname.setError("Enter your name");
            Toast.makeText(ChangeProfile.this, "Please write your middle name / initial...", Toast.LENGTH_SHORT).show();
        }else if (phone.isEmpty()) {
            phoneNumber.setError("Enter your 11 digit phone number");
            Toast.makeText(ChangeProfile.this, "Please enter your phone number...", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(add)) {
            address.setError("Enter your address");
            Toast.makeText(ChangeProfile.this, "Please write your address...", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(emailAdds)) {
            emailAdd.setError("Enter your email address");
            Toast.makeText(ChangeProfile.this, "Please write your email address...", Toast.LENGTH_SHORT).show();
        }else {
            updateDataPic(name, add, phone, emailAdds);
        }

    }

    private void updateDataPic(String name, String add, String phone, String emailAdds) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Updating Profile");
        progressDialog.setMessage("Please wait, while we are updating your account information");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        if (imageUri != null) {
            final StorageReference fileRef = storageProfilePrictureRef
                    .child(Users.getUid() + ".jpg");

            uploadTask = fileRef.putFile(imageUri);

            uploadTask.continueWithTask(new Continuation() {
                @Override
                public Object then(@NonNull Task task) throws Exception {
                    if (!task.isSuccessful()) {
                        throw task.getException();
                    }

                    return fileRef.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if (task.isSuccessful()) {
                        Uri downloadUrl = task.getResult();
                        myUrl = downloadUrl.toString();

                        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("users");

                        HashMap User = new HashMap();
                        User.put("fullname", name);
                        User.put("houseAddress", add);
                        User.put("email", emailAdds);
                        User.put("phoneNumber", phone);
                        User.put("profileImage", myUrl);
                        ref.child(Users.getUid()).updateChildren(User);

                        progressDialog.dismiss();

                        startActivity(new Intent(ChangeProfile.this, MyProfile.class));
                        Toast.makeText(ChangeProfile.this, "Profile updated successfully.", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(ChangeProfile.this, "Error.", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else {
            progressDialog.dismiss();
            Toast.makeText(this, "image is not selected.", Toast.LENGTH_SHORT).show();
        }

    }

    private void updateWithNoProfilePic() {
        String name = fullname.getText().toString();
        String add = address.getText().toString();
        String phone = phoneNumber.getText().toString();
        String emailAdds = emailAdd.getText().toString();


        if (TextUtils.isEmpty(name)) {
            fullname.setError("Enter your name");
            Toast.makeText(ChangeProfile.this, "Please write your middle name / initial...", Toast.LENGTH_SHORT).show();
        }else if (phone.isEmpty()) {
            phoneNumber.setError("Enter your 11 digit phone number");
            Toast.makeText(ChangeProfile.this, "Please enter your phone number...", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(add)) {
            address.setError("Enter your address");
            Toast.makeText(ChangeProfile.this, "Please write your address...", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(emailAdds)) {
            emailAdd.setError("Enter your email address");
            Toast.makeText(ChangeProfile.this, "Please write your email address...", Toast.LENGTH_SHORT).show();
        }else {
            updateData(name, add, phone, emailAdds);
        }
    }

    private void updateData(String name, String add, String phone, String emailAdds) {
        HashMap User = new HashMap();
        User.put("fullname", name);
        User.put("houseAddress", add);
        User.put("email", emailAdds);
        User.put("phoneNumber", phone);
        Auth = FirebaseAuth.getInstance();
        Users = Auth.getCurrentUser();

        databaseReference = FirebaseDatabase.getInstance().getReference("users");
        databaseReference.child(Users.getUid()).updateChildren(User).addOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {
                if (task.isSuccessful()){
                    Toast.makeText(ChangeProfile.this, "Successfully Updated", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ChangeProfile.this, MyProfile.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(ChangeProfile.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
/*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            imageUri = result.getUri();

            profileImage.setImageURI(imageUri);
        } else {
            Toast.makeText(this, "Please Select Image.", Toast.LENGTH_SHORT).show();

            startActivity(new Intent(ChangeProfile.this, ChangeProfile.class));
            finish();
        }
    }
*/
}