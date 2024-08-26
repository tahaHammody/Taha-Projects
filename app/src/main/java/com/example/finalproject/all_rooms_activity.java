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

import Logic.Room;
import Logic.School;
import Logic.SysData;

public class all_rooms_activity extends AppCompatActivity {

    private ImageView roomsArrow;
    private Button addBtn;
    private Button updateBtn;
    private Button deleteBtn;
    private RecyclerView roomsRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_rooms);

        List<Room> rooms = SysData.getInstance().getRooms();

        roomsArrow = (ImageView) findViewById(R.id.roomsArrow);
        addBtn = (Button) findViewById(R.id.addBtn);
        updateBtn = (Button) findViewById(R.id.updateBtn);
        deleteBtn = (Button) findViewById(R.id.deleteBtn);
        roomsRecyclerView = (RecyclerView) findViewById(R.id.roomsRecyclerView);

        roomsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        roomsRecyclerView.setAdapter(new RoomsAdapter(getApplicationContext(), rooms));

        roomsArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(all_rooms_activity.this, home_page_for_admin_activity.class);
                startActivity(intent);
            }
        });

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(all_rooms_activity.this, add_room_activity.class);
                startActivity(intent);
            }
        });

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(all_rooms_activity.this, update_room_activity.class);
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