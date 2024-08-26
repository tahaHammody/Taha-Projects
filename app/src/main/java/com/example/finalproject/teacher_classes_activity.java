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

public class teacher_classes_activity extends AppCompatActivity {

    private ImageView arrow1;
    private Button addBtn;
    private Button updateBtn2;
    private Button deleteBtn2;
    private RecyclerView teacherClassesRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacher_classes);

        List<Class> classes = new ArrayList<Class>();
        classes.add(new Class(new Date(), new Time(9), Subject.Arabic, "Third Class" ));
        classes.add(new Class(new Date(), new Time(12), Subject.Arabic, "First Class" ));
        classes.add(new Class(new Date(), new Time(14), Subject.Arabic, "Fifth Class" ));
        classes.add(new Class(new Date(), new Time(9), Subject.Arabic, "Second Class" ));
        classes.add(new Class(new Date(), new Time(17), Subject.Arabic, "Third Class" ));
        classes.add(new Class(new Date(), new Time(11), Subject.Arabic, "Sixth Class" ));
        classes.add(new Class(new Date(), new Time(13), Subject.Arabic, "Sixth Class" ));
        classes.add(new Class(new Date(), new Time(18), Subject.Arabic, "Fourth Class" ));

        arrow1 = (ImageView) findViewById(R.id.arrow1);
        addBtn = (Button) findViewById(R.id.studentsBtn);
        updateBtn2 = (Button) findViewById(R.id.teachersBtn);
        deleteBtn2 = (Button) findViewById(R.id.classesBtn);
        teacherClassesRecyclerView = (RecyclerView) findViewById(R.id.teacherClassesRecyclerView);

        teacherClassesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        teacherClassesRecyclerView.setAdapter(new ClassesAdapter(getApplicationContext(),classes));

        arrow1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(teacher_classes_activity.this, home_page_for_teacher_activity.class);
                startActivity(intent);
            }
        });

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(teacher_classes_activity.this , add_class_teacher_activity.class);
                startActivity(intent);
            }
        });

        updateBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(teacher_classes_activity.this , update_class_teacher.class);
                startActivity(intent);
            }
        });

        deleteBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}