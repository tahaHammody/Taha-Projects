package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Logic.Class;
import Logic.Enums.Subject;

public class students_classes_activity extends AppCompatActivity {

    private ImageView arrow2;
    private Button addButton8;
    private Button reviewButton;
    private Button deleteButton5;
    private RecyclerView studentClassesRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.students_classes);

        List<Class> classes = new ArrayList<Class>();
        classes.add(new Class(new Date(), new Time(9), Subject.Arabic, "Third Class" ));
        classes.add(new Class(new Date(), new Time(17), Subject.English, "Third Class" ));
        classes.add(new Class(new Date(), new Time(13), Subject.Hebrew, "Third Class" ));
        classes.add(new Class(new Date(), new Time(12), Subject.Math, "Third Class" ));
        classes.add(new Class(new Date(), new Time(14), Subject.Geography, "Third Class" ));
        classes.add(new Class(new Date(), new Time(9), Subject.Sciences, "Third Class" ));



        arrow2 = (ImageView) findViewById(R.id.arrow2);
        addButton8 = (Button) findViewById(R.id.addButton8);
        reviewButton = (Button) findViewById(R.id.reviewButton);
        deleteButton5 = (Button) findViewById(R.id.deleteButton5);
        studentClassesRecyclerView = (RecyclerView) findViewById(R.id.studentClassesRecyclerView);

        studentClassesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        studentClassesRecyclerView.setAdapter(new ClassesAdapter(getApplicationContext(),classes));

        arrow2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(students_classes_activity.this, home_page_for_student_activity.class);
                startActivity(intent);
            }
        });

        addButton8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(students_classes_activity.this , add_class_student_activity.class);
                startActivity(intent);
            }
        });

        reviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(students_classes_activity.this , student_review_activity.class);
                startActivity(intent);
            }
        });

        deleteButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }
}