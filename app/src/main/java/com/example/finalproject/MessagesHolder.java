package com.example.finalproject;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MessagesHolder extends RecyclerView.ViewHolder {

    ImageView senderImageView;
    TextView senderUserName, messageTitle, messageContent;

    public MessagesHolder(@NonNull View itemView) {
        super(itemView);
        senderImageView = itemView.findViewById(R.id.senderImageView);
        senderUserName = itemView.findViewById(R.id.senderUserName);
        messageTitle = itemView.findViewById(R.id.messageTitle);
        messageContent = itemView.findViewById(R.id.messageContent);
    }
}
