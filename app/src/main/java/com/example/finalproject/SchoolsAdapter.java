package com.example.finalproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import Logic.School;

public class SchoolsAdapter extends RecyclerView.Adapter<SchoolsHolder> {

    Context context;
    List<School> schools;

    public SchoolsAdapter(Context context, List<School> schools) {
        this.context = context;
        this.schools = schools;
    }

    @NonNull
    @Override
    public SchoolsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SchoolsHolder(LayoutInflater.from(context).inflate(R.layout.school_row, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SchoolsHolder holder, int position) {
        holder.name.setText(schools.get(position).getName());
        holder.region.setText(String.valueOf(schools.get(position).getRegion()));
        holder.opening.setText("Opening : " + String.valueOf(schools.get(position).getOpeningHour()));
        holder.closing.setText("Closing : " + String.valueOf(schools.get(position).getClosingHour()));
    }

    @Override
    public int getItemCount() {
        return schools.size();
    }
}
