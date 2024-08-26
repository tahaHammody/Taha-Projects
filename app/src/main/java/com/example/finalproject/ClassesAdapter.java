package com.example.finalproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import Logic.Class;

public class ClassesAdapter extends RecyclerView.Adapter<ClassesHolder> {

        Context context;
        List<Class> classes;

    public ClassesAdapter(Context context, List<Class> classes) {
        this.context = context;
        this.classes = classes;
    }

    @NonNull
    @Override
    public ClassesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ClassesHolder(LayoutInflater.from(context).inflate(R.layout.class_row, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ClassesHolder holder, int position) {
        holder.subject.setText(String.valueOf(classes.get(position).getSubject()));
        holder.age.setText(classes.get(position).getAge());
        holder.date.setText(String.valueOf(classes.get(position).getClassDate()));
        holder.time.setText(String.valueOf(classes.get(position).getClassTime()));
    }

    @Override
    public int getItemCount() {
        return classes.size();
    }
}
