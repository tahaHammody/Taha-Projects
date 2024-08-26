package com.example.finalproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import Logic.Review;

public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsHolder> {

    Context context;
    List<Review> reviews;

    public ReviewsAdapter(Context context, List<Review> reviews) {
        this.context = context;
        this.reviews = reviews;
    }

    @NonNull
    @Override
    public ReviewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ReviewsHolder(LayoutInflater.from(context).inflate(R.layout.reviews_row, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewsHolder holder, int position) {
        holder.classTextView.setText(String.valueOf(reviews.get(position).getClass1().getBriefInformation()));
        holder.commentTextView.setText(reviews.get(position).getComment());
        holder.reviewer.setText(String.valueOf(reviews.get(position).getReviewer().getUserName()));
        holder.reviewImageView.setImageResource(reviews.get(position).getDegree());
    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }
}
