package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SearchView;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Logic.Class;
import Logic.Enums.Subject;
import Logic.SysData;

public class add_class_student_activity extends AppCompatActivity {
    private SearchView searchTeacher;
    private SearchView searchSubject;
    private ImageView arrow8;
    private Button requestButton;
    private RecyclerView addClassStudentRecyclerView;

    List<Class> classes = SysData.getInstance().getClasses();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_class_student);

        classes.add(new Class(new Date(), new Time(9), Subject.Arabic, "Third Class" ));
        classes.add(new Class(new Date(), new Time(12), Subject.Math, "First Class" ));
        classes.add(new Class(new Date(), new Time(14), Subject.Geography, "Fifth Class" ));
        classes.add(new Class(new Date(), new Time(9), Subject.Sciences, "Second Class" ));
        classes.add(new Class(new Date(), new Time(17), Subject.English, "Third Class" ));
        classes.add(new Class(new Date(), new Time(11), Subject.Computers, "Sixth Class" ));
        classes.add(new Class(new Date(), new Time(13), Subject.Hebrew, "Sixth Class" ));
        classes.add(new Class(new Date(), new Time(18), Subject.Arabic, "Fourth Class" ));

        searchTeacher = (SearchView) findViewById(R.id.searchTeacher);
        searchSubject = (SearchView) findViewById(R.id.searchSubject);
        arrow8 = (ImageView) findViewById(R.id.arrow8);
        requestButton = (Button) findViewById(R.id.requestButton);
        addClassStudentRecyclerView = (RecyclerView) findViewById(R.id.addClassStudentRecyclerView);

        addClassStudentRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        addClassStudentRecyclerView.setAdapter(new ClassesAdapter(getApplicationContext(),classes));


        //TODO
        searchSubject.setQueryHint("Search By Subject");
        searchTeacher.setQueryHint("Search By Teacher");

        arrow8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(add_class_student_activity.this, students_classes_activity.class);
                startActivity(intent);
            }
        });

        requestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(add_class_student_activity.this, request_class_activity.class);
                startActivity(intent);
            }
        });
    }
}