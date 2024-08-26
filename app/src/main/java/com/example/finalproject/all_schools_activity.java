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
import java.util.List;

import Logic.Enums.Region;
import Logic.School;
import Logic.SysData;

public class all_schools_activity extends AppCompatActivity {

    private ImageView schoolsArrow;
    private Button addBtn;
    private Button updateBtn;
    private Button deleteBtn;
    private RecyclerView schoolsRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_schools);

        List<School> schools = SysData.getInstance().getSchools();

        schoolsArrow = (ImageView) findViewById(R.id.schoolsArrow);
        addBtn = (Button) findViewById(R.id.addBtn);
        updateBtn = (Button) findViewById(R.id.updateBtn);
        deleteBtn = (Button) findViewById(R.id.deleteBtn);
        schoolsRecyclerView = (RecyclerView) findViewById(R.id.schoolsRecyclerView);

        schoolsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        schoolsRecyclerView.setAdapter(new SchoolsAdapter(getApplicationContext(),schools));

        schoolsArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(all_schools_activity.this, home_page_for_admin_activity.class);
                startActivity(intent);
            }
        });

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(all_schools_activity.this, add_school_admin_activity.class);
                startActivity(intent);
            }
        });

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(all_schools_activity.this, update_school_activity.class);
                startActivity(intent);
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}