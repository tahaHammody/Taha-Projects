package com.example.finalproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import Logic.Student;

public class studentsAdapter extends RecyclerView.Adapter<studentsViewHolder> {

    Context context;
    List<Student> students;

    public studentsAdapter(Context context, List<Student> students) {
        this.context = context;
        this.students = students;
    }

    @NonNull
    @Override
    public studentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new studentsViewHolder(LayoutInflater.from(context).inflate(R.layout.students_row, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull studentsViewHolder holder, int position) {
        holder.studentUserName.setText(students.get(position).getUserName());
        holder.studentImageView.setImageResource(students.get(position).getImage());
        holder.studentAge.setText((String.valueOf(students.get(position).getAge())));

    }

    @Override
    public int getItemCount() {
        return students.size();
    }

}
