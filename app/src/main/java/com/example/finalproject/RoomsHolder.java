package com.example.finalproject;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RoomsHolder extends RecyclerView.ViewHolder {

    TextView school, floor, capacity, num;

    public RoomsHolder(@NonNull View itemView) {
        super(itemView);
        school = itemView.findViewById(R.id.school);
        floor = itemView.findViewById(R.id.floor);
        capacity = itemView.findViewById(R.id.capacity);
        num = itemView.findViewById(R.id.num);
    }
}
