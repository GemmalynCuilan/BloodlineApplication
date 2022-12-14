package com.example.bloodlineapplication.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bloodlineapplication.R;
import com.example.bloodlineapplication.model.User;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class UserAdapter extends FirebaseRecyclerAdapter<User, UserAdapter.myViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public UserAdapter(@NonNull FirebaseRecyclerOptions<User> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull User model) {
        holder.fullname.setText(model.getFullname());
        holder.age.setText(model.getAge()+" years old.");
        holder.address.setText(model.getAddress());
        holder.email.setText(model.getEmail());
        holder.blood.setText(model.getBlood());
        holder.bloodGroups.setText(model.getBloodGroups());

    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.user_layout_item,parent, false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{

     TextView fullname,age, email, address, bloodGroups, blood;

     public myViewHolder(@NonNull View itemView) {
         super(itemView);
         fullname =(TextView) itemView.findViewById(R.id.fullnametext);
         age =(TextView) itemView.findViewById(R.id.agetext);
         email =(TextView) itemView.findViewById(R.id.emailtext);
         address =(TextView) itemView.findViewById(R.id.addresstext);
         bloodGroups =(TextView) itemView.findViewById(R.id.bloodGroupstext);
         blood =(TextView) itemView.findViewById(R.id.bloodtext);

     }
 }
}
