package com.example.bloodlineapplication.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bloodlineapplication.R;
import com.example.bloodlineapplication.update.User;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder>{
    private Context context;
    private List<User> userList;
    public UserAdapter(Context context, List<User> userList){
        this.context = context;
        this.userList = userList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.user_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final User user = userList.get(position);
        holder.name.setText(user.getName());
        holder.userAddress.setText(user.getUserAddress());
        holder.emailAdd.setText(user.getEmailAdd());
        holder.bgroup.setText(user.getBgroup());
        holder.bloodDr.setText(user.getBloodDr());

        if(user.getBloodDr().equals("Blood Donor")){
            holder.emailNow.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView name, userAddress, emailAdd, bgroup, bloodDr;
        public Button emailNow;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.fullname);
            userAddress = itemView.findViewById(R.id.address);
            emailAdd = itemView.findViewById(R.id.email);
            bgroup = itemView.findViewById(R.id.bloodGroups);
            bloodDr = itemView.findViewById(R.id.blood);

        }
    }
}
