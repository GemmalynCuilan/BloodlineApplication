package com.example.bloodlineapplication.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bloodlineapplication.R;
import com.example.bloodlineapplication.update.UserReq;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class BloodRequestAdapter extends FirebaseRecyclerAdapter<UserReq, BloodRequestAdapter.PostHolder> {


    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public BloodRequestAdapter(@NonNull FirebaseRecyclerOptions<UserReq> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull PostHolder holder, int position, @NonNull UserReq model) {
        holder.bloodgroup.setText("I Need " + model.getBloodGroups());
        holder.userName.setText("Requested by " + model.getName());
        holder.posted.setText("Posted on: "+model.getTime()+" , "+model.getDate());
        holder.Address.setText("From: " + model.getAddress());
        holder.userNum.setText("Message me:" + model.getNumber());

    }

    @NonNull
    @Override
    public PostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.request_list_item,parent, false);
        return new PostHolder(view);
    }

    class PostHolder extends  RecyclerView.ViewHolder{

        TextView  bloodgroup, Address, userName, time, date, posted, userNum;

        public PostHolder(@NonNull View itemView) {
            super(itemView);
            userName = (TextView) itemView.findViewById(R.id.targetSN);
            bloodgroup = (TextView) itemView.findViewById(R.id.targetBG);
            Address = (TextView) itemView.findViewById(R.id.reqstLocation);
            userNum = (TextView) itemView.findViewById(R.id.messnum);
            posted = (TextView) itemView.findViewById(R.id.posted);
        }
    }

}
