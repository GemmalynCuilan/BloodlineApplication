package com.example.bloodlineapplication.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.bloodlineapplication.R;
import com.example.bloodlineapplication.adapters.BloodRequestAdapter;
import com.example.bloodlineapplication.update.CustomUserData;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewRequestActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    BloodRequestAdapter bloodRequestAdapter;
    List<CustomUserData> postLists;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_request);

        recyclerView = findViewById(R.id.recyleposts);
        databaseReference = FirebaseDatabase.getInstance().getReference("posts");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

       postLists = new ArrayList<>();
       bloodRequestAdapter = new BloodRequestAdapter(this, postLists);
       recyclerView.setAdapter(bloodRequestAdapter);

       /* databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
               for(DataSnapshot snaps)
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        })*/
    }
}