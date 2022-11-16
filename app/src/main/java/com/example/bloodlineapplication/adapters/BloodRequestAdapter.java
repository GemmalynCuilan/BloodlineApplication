package com.example.bloodlineapplication.adapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bloodlineapplication.update.CustomUserData;
import com.example.bloodlineapplication.R;

import java.util.List;
public class BloodRequestAdapter extends RecyclerView.Adapter<BloodRequestAdapter.PostHolder> {


    private List<CustomUserData> postLists;

    public class PostHolder extends RecyclerView.ViewHolder
    {
        TextView Name, Bloodgroups, Address, Email, posted;

        public PostHolder(@NonNull View itemView) {
            super(itemView);

            Name = itemView.findViewById(R.id.fullname);
            Email = itemView.findViewById(R.id.email);
            Bloodgroups = itemView.findViewById(R.id.bloodGroups);
            Address = itemView.findViewById(R.id.address);
            //posted = itemView.findViewById(R.id.posted);

        }
    }

    public BloodRequestAdapter(List<CustomUserData> postLists)
    {
        this.postLists = postLists;
    }

    @Override
    public PostHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View listitem = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.request_list_item, viewGroup, false);

        return new PostHolder(listitem);
    }

    @Override
    public void onBindViewHolder(PostHolder postHolder, int i) {

        if(i%2==0)
        {
            postHolder.itemView.setBackgroundColor(Color.parseColor("#C13F31"));
        }
        else
        {
            postHolder.itemView.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }

        CustomUserData customUserData = postLists.get(i);
        postHolder.Name.setText("Posted by: "+customUserData.getName());
        postHolder.Address.setText("From: "+customUserData.getAddress());
        postHolder.Bloodgroups.setText("Needs "+customUserData.getBloodGroups());
        //postHolder.posted.setText("Posted on:"+customUserData.getTime()+", "+customUserData.getDate());
        postHolder.Email.setText(customUserData.getEmail());

    }

    @Override
    public int getItemCount() {
        return postLists.size();
    }
}