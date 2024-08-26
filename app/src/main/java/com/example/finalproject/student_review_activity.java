package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.RatingBar;
import android.widget.Toast;

import Logic.Class;
import Logic.Review;
import Logic.SysData;
import Logic.User;

public class student_review_activity extends AppCompatActivity {

    private ImageView arrow12;
    private Button reviewBtn;
    private RatingBar ratingBar;
    private MultiAutoCompleteTextView notesEditText;
    private User user;
    private Class class1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_review);

        arrow12 = (ImageView) findViewById(R.id.arrow12);
        reviewBtn = (Button) findViewById(R.id.reviewBtn);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        notesEditText = (MultiAutoCompleteTextView) findViewById(R.id.notesEditText);

        arrow12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(student_review_activity.this, students_classes_activity.class);
                startActivity(intent);
            }
        });

        reviewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SysData.getInstance().getReviews().add(new Review(ratingBar.getNumStars(), user, notesEditText.getText().toString(), class1));
                Toast toast = Toast.makeText(getApplicationContext(), "your Review added successfully", Toast.LENGTH_SHORT);
                toast.show();
            }
        });


    }
}