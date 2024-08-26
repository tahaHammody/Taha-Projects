package com.example.finalproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import Logic.Teacher;

public class TeachersAdapter extends RecyclerView.Adapter<TeachersViewHolder> {

    Context context;
    List<Teacher> teachers;

    public TeachersAdapter(Context context, List<Teacher> teachers) {
        this.context = context;
        this.teachers = teachers;
    }

    @NonNull
    @Override
    public TeachersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TeachersViewHolder(LayoutInflater.from(context).inflate(R.layout.teachers_row, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TeachersViewHolder holder, int position) {
        holder.teacherUserName.setText(teachers.get(position).getUserName());
        holder.teacherSubject.setText(String.valueOf(teachers.get(position).getSubject()));
        holder.teacherImageView.setImageResource(teachers.get(position).getImage());

    }

    @Override
    public int getItemCount() {
        return teachers.size();
    }
}
