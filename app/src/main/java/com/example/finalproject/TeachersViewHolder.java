package com.example.finalproject;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TeachersViewHolder extends RecyclerView.ViewHolder {

    ImageView teacherImageView;
    TextView teacherUserName, teacherSubject;

    public TeachersViewHolder(@NonNull View itemView) {
        super(itemView);
        teacherImageView = itemView.findViewById(R.id.teacherImageView);
        teacherUserName = itemView.findViewById(R.id.teacherUserName);
        teacherSubject = itemView.findViewById(R.id.teacherSubject);
    }
}
