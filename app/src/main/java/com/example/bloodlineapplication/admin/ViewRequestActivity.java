package com.example.bloodlineapplication.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.bloodlineapplication.R;
import com.example.bloodlineapplication.adapters.BloodRequestAdapter;
import com.example.bloodlineapplication.adapters.UserAdapter;
import com.example.bloodlineapplication.model.User;
import com.example.bloodlineapplication.update.UserReq;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class ViewRequestActivity extends AppCompatActivity {

    private RecyclerView recyclerposts;
    private List<User> userList;
    private DatabaseReference userRef;
    private BloodRequestAdapter bloodRequestAdapter;
    private FirebaseAuth Auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_request);

        ImageButton arrowBack = (ImageButton) findViewById(R.id.arrowback_profile);
        arrowBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewRequestActivity.this, AdminDashboard.class);
                startActivity(intent);

            }
        });
        recyclerposts = (RecyclerView)findViewById(R.id.recyclerposts);
        recyclerposts.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<UserReq> options = new FirebaseRecyclerOptions.Builder<UserReq>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("posts"), UserReq.class).build();

        bloodRequestAdapter= new BloodRequestAdapter(options);
        recyclerposts.setAdapter(bloodRequestAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
       bloodRequestAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        bloodRequestAdapter.stopListening();
    }
}