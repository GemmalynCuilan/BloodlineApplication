package com.example.bloodlineapplication.admin;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bloodlineapplication.R;

public class fragmentR extends Fragment {

    View view;

    public fragmentR(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_r,container,false);
        return view;
    }
}