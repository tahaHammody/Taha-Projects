package com.example.finalproject;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SchoolsHolder extends RecyclerView.ViewHolder {

    TextView name, opening, region, closing;

    public SchoolsHolder(@NonNull View itemView) {
        super(itemView);

        name = itemView.findViewById(R.id.name);
        opening = itemView.findViewById(R.id.opening);
        region = itemView.findViewById(R.id.region);
        closing = itemView.findViewById(R.id.closing);
    }
}
