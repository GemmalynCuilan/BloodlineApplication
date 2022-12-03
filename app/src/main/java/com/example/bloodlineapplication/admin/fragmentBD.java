package com.example.bloodlineapplication.admin;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bloodlineapplication.R;
import com.example.bloodlineapplication.adapters.UserAdapter;
import com.example.bloodlineapplication.model.User;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class fragmentBD extends Fragment {

    private UserAdapter userAdapter;
    private RecyclerView recyclerView;
    private List<User> userList;
    public fragmentBD(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_b_d,
               container,
               false);
       recyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);
       recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        FirebaseRecyclerOptions<User> options = new FirebaseRecyclerOptions.Builder<User>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("users"), User.class).build();


        userAdapter = new UserAdapter(options);
        recyclerView.setAdapter(userAdapter);
       return view;

    }

@Override
public void onStart() {
    super.onStart();
    userAdapter.startListening();
}

    @Override
    public void onStop() {
        super.onStop();
        userAdapter.stopListening();
    }
}