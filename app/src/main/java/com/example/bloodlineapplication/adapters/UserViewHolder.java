package com.example.bloodlineapplication.adapters;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bloodlineapplication.R;

public class UserViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

  public TextView fullname, address, email, bloodGroups, blood;
  public ItemClickListener listener;
    public UserViewHolder(@NonNull View itemView) {
        super(itemView);
      fullname= (TextView) itemView.findViewById(R.id.fullname);
      address = (TextView) itemView.findViewById(R.id.address);
      email = (TextView) itemView.findViewById(R.id.email);
      bloodGroups = (TextView) itemView.findViewById(R.id.bloodGroups);
      blood = (TextView) itemView.findViewById(R.id.blood);

    }

    @Override
    public void onClick(View view) {
      listener.onClick(view, getAdapterPosition(), false);
    }
  public void setItemClickListener(ItemClickListener listener){
    this.listener = listener;
  }

}
