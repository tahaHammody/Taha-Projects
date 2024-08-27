package com.example.sheltered_living.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sheltered_living.R;
import com.example.sheltered_living.callbacks.OnDeleteClickListener;
import com.example.sheltered_living.models.Feedback;

import java.util.List;

public class FeedbacksAdapter extends RecyclerView.Adapter<FeedbacksAdapter.FeedbackVH> {

    private List<Feedback> feedbacks;
    private OnDeleteClickListener onDeleteClickListener;

    public FeedbacksAdapter(List<Feedback> feedbacks, OnDeleteClickListener listener) {
        this.feedbacks = feedbacks;
        this.onDeleteClickListener = listener;
    }

    @NonNull
    @Override
    public FeedbackVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FeedbackVH(LayoutInflater.from(parent.getContext()).inflate(R.layout.feedback_layout_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FeedbackVH holder, int position) {
        holder.room.setText(String.valueOf(feedbacks.get(position).getRoomNumber()));
        holder.description.setText(feedbacks.get(position).getMessage());
        holder.name.setText(feedbacks.get(position).getName());
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDeleteClickListener.onDeleteClicked(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return feedbacks.size();
    }

    public static class FeedbackVH extends RecyclerView.ViewHolder {

        TextView room;
        TextView name;
        TextView description;
        Button delete;

        public FeedbackVH(@NonNull View itemView) {
            super(itemView);
            room = itemView.findViewById(R.id.room);
            name = itemView.findViewById(R.id.name);
            description = itemView.findViewById(R.id.description);
            delete = itemView.findViewById(R.id.delete);
        }
    }
}
