package com.example.bloodlineapplication.admin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bloodlineapplication.R;
import com.example.bloodlineapplication.model.User;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    Context mContext;
    List<User> mData;

    public RecyclerViewAdapter(Context mContext, List<User> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(mContext).inflate(R.layout.user_layout_item, parent, false);
        MyViewHolder vHolder = new MyViewHolder(view);
        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.fullname.setText(mData.get(position).getFullname());
        holder.age.setText(mData.get(position).getAge());
        holder.address.setText(mData.get(position).getAddress());
        holder.email.setText(mData.get(position).getEmail());
        holder.blood.setText(mData.get(position).getBlood());
        holder.bloodGroups.setText(mData.get(position).getBloodGroups());
        holder.serialNum.setText(mData.get(position).getSerialNum());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView fullname,age, email, address, bloodGroups, blood, serialNum;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            fullname =(TextView) itemView.findViewById(R.id.fullnametext);
            age =(TextView) itemView.findViewById(R.id.agetext);
            email =(TextView) itemView.findViewById(R.id.emailtext);
            address =(TextView) itemView.findViewById(R.id.addresstext);
            bloodGroups =(TextView) itemView.findViewById(R.id.bloodGroupstext);
            blood =(TextView) itemView.findViewById(R.id.bloodtext);
            serialNum =(TextView) itemView.findViewById(R.id.serialNumtext);
        }
    }
}
