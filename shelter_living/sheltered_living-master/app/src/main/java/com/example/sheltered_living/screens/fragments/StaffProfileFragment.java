package com.example.sheltered_living.screens.fragments;

import android.app.Fragment;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.sheltered_living.R;
import com.example.sheltered_living.SessionManager;


public class StaffProfileFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_staff_profile, container, false);

        ImageView backButton = view.findViewById(R.id.backButton3);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Glide.with(getContext())
                .load(SessionManager.staffMember.getImageUrl())
                .circleCrop()
                .placeholder(R.drawable.baseline_person_24)
                .into((ImageView) view.findViewById(R.id.profileImage));

        ((TextView)view.findViewById(R.id.nameTextView)).setText(SessionManager.staffMember.getName());
        ((TextView)view.findViewById(R.id.emailTextView)).setText(SessionManager.staffMember.getEmail());
        ((TextView)view.findViewById(R.id.phoneTextView)).setText(SessionManager.staffMember.getPhone());


    }
}