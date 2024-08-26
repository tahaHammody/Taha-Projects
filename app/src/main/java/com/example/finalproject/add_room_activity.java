package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

import Logic.Class;
import Logic.Enums.Equipments;
import Logic.Room;
import Logic.SysData;

public class add_room_activity extends AppCompatActivity {

    private ImageView arrow5;
    private TextView schoolStar1;
    private TextView capacityStar;
    private TextView floorStar;
    private TextView numberStar;
    private Spinner schoolSpinner;
    private EditText capacityEditText;
    private EditText floorEditText;
    private EditText numberEditText;
    private Button addBtn;
    private CheckBox computer;
    private CheckBox closet;
    private CheckBox artTools;
    private CheckBox hanger;
    private CheckBox teacherTable;
    private CheckBox tables;
    private CheckBox chairs;
    private CheckBox projector;
    private CheckBox tv;
    private CheckBox conditioner;
    private CheckBox games;
    private CheckBox board;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_room);

        arrow5 = (ImageView) findViewById(R.id.arrow5);
        schoolStar1 = (TextView) findViewById(R.id.schoolStar1);
        capacityStar = (TextView) findViewById(R.id.capacityStar);
        floorStar = (TextView) findViewById(R.id.floorStar);
        numberStar = (TextView) findViewById(R.id.numberStar);
        schoolSpinner = (Spinner) findViewById(R.id.schoolSpinner);
        capacityEditText = (EditText) findViewById(R.id.capacityEditText);
        floorEditText = (EditText) findViewById(R.id.floorEditText);
        numberEditText = (EditText) findViewById(R.id.numberEditText);
        addBtn = (Button) findViewById(R.id.addBtn);
        computer = (CheckBox) findViewById(R.id.computer);
        closet = (CheckBox) findViewById(R.id.closet);
        artTools = (CheckBox) findViewById(R.id.artTools);
        hanger = (CheckBox) findViewById(R.id.hanger);
        teacherTable = (CheckBox) findViewById(R.id.teacherTable);
        tables = (CheckBox) findViewById(R.id.tables);
        chairs = (CheckBox) findViewById(R.id.chairs);
        projector = (CheckBox) findViewById(R.id.projector);
        tv = (CheckBox) findViewById(R.id.tv);
        conditioner = (CheckBox) findViewById(R.id.conditioner);
        games = (CheckBox) findViewById(R.id.games);
        board = (CheckBox) findViewById(R.id.board);

        arrow5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(add_room_activity.this, all_rooms_activity.class);
                    startActivity(intent);
            }
        });

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<Equipments, Boolean> equipments = new HashMap<Equipments, Boolean>();
                equipments.put(Equipments.Computer, computer.isChecked());
                equipments.put(Equipments.Projector, projector.isChecked());
                equipments.put(Equipments.TV, tv.isChecked());
                equipments.put(Equipments.Conditioner, conditioner.isChecked());
                equipments.put(Equipments.Games, games.isChecked());
                equipments.put(Equipments.Board, board.isChecked());
                equipments.put(Equipments.Closet, closet.isChecked());
                equipments.put(Equipments.ArtTools, artTools.isChecked());
                equipments.put(Equipments.Hanger, hanger.isChecked());
                equipments.put(Equipments.TeacherTable, teacherTable.isChecked());
                equipments.put(Equipments.Tables, tables.isChecked());
                equipments.put(Equipments.Chairs, chairs.isChecked());

                int check = 1;

                if(capacityEditText.getText().toString().equals("")){
                    capacityStar.setVisibility(View.VISIBLE);
                    check = 0;
                }
                if(floorEditText.getText().toString().equals("")){
                    floorStar.setVisibility(View.VISIBLE);
                    check = 0;
                }
                if(numberEditText.getText().toString().equals("")){
                    numberStar.setVisibility(View.VISIBLE);
                    check = 0;
                }
                else if(check == 1){
                    SysData.getInstance().getRooms().add(new Room( SysData.getInstance().getSchoolByName(schoolSpinner.getSelectedItem().toString()),Integer.parseInt(capacityEditText.getText().toString()), equipments, null, Integer.parseInt(floorEditText.getText().toString()),Integer.parseInt(numberEditText.getText().toString())));
                    Toast toast = Toast.makeText(getApplicationContext(), "added successfully", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

    }
}