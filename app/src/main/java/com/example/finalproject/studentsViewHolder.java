package com.example.finalproject;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.R;

public class studentsViewHolder extends RecyclerView.ViewHolder {

    ImageView studentImageView;
    TextView studentUserName, studentAge;

    public studentsViewHolder(@NonNull View itemView) {
        super(itemView);

        studentImageView = itemView.findViewById(R.id.studentImageView);
        studentUserName = itemView.findViewById(R.id.studentUserName);
        studentAge = itemView.findViewById(R.id.studentAge);

    }
}
