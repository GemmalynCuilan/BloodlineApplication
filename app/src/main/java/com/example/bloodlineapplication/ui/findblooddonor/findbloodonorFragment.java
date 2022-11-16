package com.example.bloodlineapplication.ui.findblooddonor;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.bloodlineapplication.R;
import com.example.bloodlineapplication.databinding.FragmentFindblooddonorBinding;
import com.example.bloodlineapplication.databinding.FragmentFindblooddonorBinding;

public class findbloodonorFragment extends Fragment {


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
      ;
        View root = inflater.inflate(R.layout.fragment_findblooddonor, container, false);


        return root;
    }

}