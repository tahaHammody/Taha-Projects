package com.example.sheltered_living.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sheltered_living.R;
import com.example.sheltered_living.SessionManager;
import com.example.sheltered_living.callbacks.OnDeleteClickListener;
import com.example.sheltered_living.callbacks.OnUpdateClickListener;
import com.example.sheltered_living.models.Task;

import java.util.ArrayList;
import java.util.List;

public class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.TaskVM> {

    private List<Task> tasks = new ArrayList<>();
    private OnDeleteClickListener deleteListener;
    private OnUpdateClickListener updateClickListener;
    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public TasksAdapter(OnDeleteClickListener listener, OnUpdateClickListener updateClickListener) {
        this.deleteListener = listener;
        this.updateClickListener = updateClickListener;
    }

    @NonNull
    @Override
    public TaskVM onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TaskVM(LayoutInflater.from(parent.getContext()).inflate(R.layout.task_manager_item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TaskVM holder, int position) {
        holder.title.setText(tasks.get(position).getTitle());
        holder.date.setText(tasks.get(position).getDate());
        holder.description.setText(tasks.get(position).getDescription());
        holder.deleteBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteListener.onDeleteClicked(holder.getAdapterPosition());
            }
        });
        holder.updateBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateClickListener.onUpdateClicked(holder.getAdapterPosition());
            }
        });

        if (SessionManager.isManagerSession()) {
            holder.deleteBT.setVisibility(View.VISIBLE);
            holder.updateBT.setVisibility(View.VISIBLE);
        } else {
            holder.deleteBT.setVisibility(View.GONE);
            holder.updateBT.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    static class TaskVM extends RecyclerView.ViewHolder{

        TextView description;
        TextView date;
        TextView title;
        Button deleteBT;
        Button updateBT;


        public TaskVM(@NonNull View itemView) {
            super(itemView);
            description = itemView.findViewById(R.id.description);
            date = itemView.findViewById(R.id.date);
            title = itemView.findViewById(R.id.title);
            deleteBT = itemView.findViewById(R.id.delete);
            updateBT = itemView.findViewById(R.id.update);
        }
    }
}
