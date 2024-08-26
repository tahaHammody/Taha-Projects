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
import Logic.SysData;

public class all_classes_activity extends AppCompatActivity {

    private ImageView arrow_left_by_streamlinehq;
    private Button addBtn;
    private Button updateBtn2;
    private Button deleteBtn2;
    private RecyclerView classesRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_classes);

        List<Class> classes = SysData.getInstance().getClasses();


        arrow_left_by_streamlinehq = (ImageView) findViewById(R.id.arrow_left_by_streamlinehq);
        addBtn = (Button) findViewById(R.id.studentsBtn);
        updateBtn2 = (Button) findViewById(R.id.teachersBtn);
        deleteBtn2 = (Button) findViewById(R.id.classesBtn);
        classesRecyclerView = (RecyclerView) findViewById(R.id.classesRecyclerView);

        classesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        classesRecyclerView.setAdapter(new ClassesAdapter(getApplicationContext(),classes));

        arrow_left_by_streamlinehq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(all_classes_activity.this, home_page_for_admin_activity.class);
                startActivity(intent);
            }
        });

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(all_classes_activity.this, add_class_admin_activity.class);
                startActivity(intent);
            }
        });

        updateBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(all_classes_activity.this, update_class_admin_activity.class);
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