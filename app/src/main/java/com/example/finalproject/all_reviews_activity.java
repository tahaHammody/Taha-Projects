package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import Logic.Class;
import Logic.Review;
import Logic.SysData;
import Logic.User;

public class all_reviews_activity extends AppCompatActivity {

    private ImageView arrow_left_by_streamlinehq;
    private RecyclerView reviewsRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_reviews);

        List<Review> reviews = SysData.getInstance().getReviews();

        arrow_left_by_streamlinehq = (ImageView) findViewById(R.id.arrow_left_by_streamlinehq);
        reviewsRecyclerView = (RecyclerView) findViewById(R.id.reviewsRecyclerView);

        reviewsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        reviewsRecyclerView.setAdapter(new ReviewsAdapter(getApplicationContext(), reviews));

        arrow_left_by_streamlinehq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(all_reviews_activity.this, home_page_for_admin_activity.class);
                startActivity(intent);
            }
        });
    }
}