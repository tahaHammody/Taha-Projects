package com.example.finalproject;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ClassesHolder extends RecyclerView.ViewHolder {

    TextView subject, date, age, time;

    public ClassesHolder(@NonNull View itemView) {
        super(itemView);

        subject = itemView.findViewById(R.id.subject);
        date = itemView.findViewById(R.id.date);
        age = itemView.findViewById(R.id.age);
        time = itemView.findViewById(R.id.time);
    }
}
