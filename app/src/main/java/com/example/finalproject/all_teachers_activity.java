package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import Logic.Enums.Subject;
import Logic.SysData;
import Logic.Teacher;

public class all_teachers_activity extends AppCompatActivity {
    private ImageView arrow_left_by_streamlinehq;
    private Button addBtn;
    private Button updateBtn2;
    private Button deleteBtn2;
    private RecyclerView teachersRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_teachers);

        arrow_left_by_streamlinehq = (ImageView) findViewById(R.id.arrow_left_by_streamlinehq);
        addBtn = (Button) findViewById(R.id.studentsBtn);
        updateBtn2 = (Button) findViewById(R.id.teachersBtn);
        deleteBtn2 = (Button) findViewById(R.id.classesBtn);
        teachersRecyclerView = (RecyclerView) findViewById(R.id.teachersRecyclerView);

        List<Teacher> teachers = SysData.getInstance().getTeachers();

        teachersRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        teachersRecyclerView.setAdapter(new TeachersAdapter(getApplicationContext(), teachers));

        arrow_left_by_streamlinehq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(all_teachers_activity.this, home_page_for_admin_activity.class);
                startActivity(intent);
            }
        });

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(all_teachers_activity.this, add_teacher_activity.class);
                startActivity(intent);
            }
        });

        updateBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(all_teachers_activity.this, update_teacher_activity.class);
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