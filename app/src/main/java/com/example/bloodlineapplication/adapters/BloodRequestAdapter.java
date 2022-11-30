package com.example.bloodlineapplication.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bloodlineapplication.update.CustomUserData;
import com.example.bloodlineapplication.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.ArrayList;
import java.util.List;
public class BloodRequestAdapter extends RecyclerView.Adapter<BloodRequestAdapter.PostHolder> {

    Context context;
    List<CustomUserData> postLists;

    public BloodRequestAdapter(Context context, List<CustomUserData> postLists) {
        this.context = context;
        this.postLists = postLists;
    }

     @NonNull
    @Override
    public PostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.request_list_item,parent, false);
        return new PostHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PostHolder holder, int i) {
        CustomUserData customUserData = postLists.get(i);
        holder.Address.setText("From: "+customUserData.getAddress()+", "+customUserData.getAddress());
        holder.bloodgroup.setText("Needs "+customUserData.getBloodGroups());


    }

    @Override
    public int getItemCount() {
        return postLists.size();
    }

    public static class PostHolder extends  RecyclerView.ViewHolder{

        TextView  bloodgroup, Address, serialNum, posted;
        public PostHolder(@NonNull View itemView) {
            super(itemView);
            serialNum = itemView.findViewById(R.id.targetSN);
            bloodgroup = itemView.findViewById(R.id.targetBG);
            Address = itemView.findViewById(R.id.reqstLocation);
            posted = itemView.findViewById(R.id.posted);
        }
    }

}
