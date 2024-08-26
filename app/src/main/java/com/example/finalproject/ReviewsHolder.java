package com.example.finalproject;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ReviewsHolder extends RecyclerView.ViewHolder {
    ImageView reviewImageView;
    TextView classTextView, commentTextView, reviewer;

    public ReviewsHolder(@NonNull View itemView) {
        super(itemView);

        reviewImageView = itemView.findViewById(R.id.reviewImageView);
        classTextView = itemView.findViewById(R.id.classTextView);
        commentTextView = itemView.findViewById(R.id.commentTextView);
        reviewer = itemView.findViewById(R.id.reviewer);
    }
}
