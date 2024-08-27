package com.example.sheltered_living.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.sheltered_living.R;
import com.example.sheltered_living.SessionManager;
import com.example.sheltered_living.callbacks.OnDeleteClickListener;
import com.example.sheltered_living.models.Manager;
import com.example.sheltered_living.models.Message;

import java.util.List;

public class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.MessageVH> {

    private List<Message> messageList;
    private OnDeleteClickListener onDeleteClickListener;

    public MessagesAdapter(List<Message> messageList, OnDeleteClickListener listener) {
        this.messageList = messageList;
        this.onDeleteClickListener = listener;
    }

    @NonNull
    @Override
    public MessageVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MessageVH(LayoutInflater.from(parent.getContext()).inflate(R.layout.message_layout_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MessageVH holder, int position) {
        holder.message.setText(messageList.get(position).getMessage());
        holder.name.setText(messageList.get(position).getStaffMember().getName());
        Glide.with(holder.itemView.getContext())
                .load(messageList.get(position).getStaffMember().getImageUrl())
                .circleCrop()
                .placeholder(R.drawable.baseline_person_24)
                .into(holder.imageView);
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDeleteClickListener.onDeleteClicked(position);
            }
        });
    }


    @Override
    public int getItemCount() {
        return messageList.size();
    }

    public static class MessageVH extends RecyclerView.ViewHolder {

        TextView name;
        TextView message;
        ImageView imageView;
        Button delete;
        public MessageVH(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            message = itemView.findViewById(R.id.description);
            imageView = itemView.findViewById(R.id.image);
            delete = itemView.findViewById(R.id.delete);

        }
    }
}
