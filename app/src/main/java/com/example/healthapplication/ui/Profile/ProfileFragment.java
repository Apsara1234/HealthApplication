package com.example.healthapplication.ui.Profile;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.healthapplication.ProfileActivity;
import com.example.healthapplication.R;


public class ProfileFragment extends Fragment {



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        Intent intent = new Intent(getContext(), ProfileActivity.class);
        startActivity(intent);

        return root;
    }
}
