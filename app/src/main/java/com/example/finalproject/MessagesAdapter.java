package com.example.finalproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import Logic.Message;

public class MessagesAdapter extends RecyclerView.Adapter<MessagesHolder> {
    Context context;
    List<Message> messages;

    public MessagesAdapter(Context context, List<Message> messages) {
        this.context = context;
        this.messages = messages;
    }

    @NonNull
    @Override
    public MessagesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MessagesHolder(LayoutInflater.from(context).inflate(R.layout.message_row, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MessagesHolder holder, int position) {
        holder.senderUserName.setText(messages.get(position).getSender().getUserName());
        holder.messageTitle.setText(messages.get(position).getTitle());
        holder.messageContent.setText(messages.get(position).getContent());
        holder.senderImageView.setImageResource(messages.get(position).getSender().getImage());
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }
}
