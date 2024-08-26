package com.example.finalproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import Logic.Class;
import Logic.SysData;

public class update_class_admin_activity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private ImageView arrow5;
    private Button updateBtn;
    private Spinner teacherSpinner;
    private MultiAutoCompleteTextView multiAutoCompleteTextView;
    private EditText notesEditText;
    private Spinner subjectSpinner;
    private Spinner durationSpinner;
    private Spinner ageSpinner;
    private CheckBox everyWeekCheckBox;
    private CheckBox everyDayCheckBox;
    private Spinner schoolsSpinner;
    private Button roomPickerBtn;
    private Button datePickerBtn;
    private Button hourPickerBtn;
    private TextView teacherStar;
    private TextView studentsStar;
    private TextView dateStar;
    private TextView schoolStar;
    private TextView hourStar;
    private TextView roomStar;
    private TextView descriptionStar;
    private TextView subjectStar;
    private TextView durationStar;
    private TextView ageStar;
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private TextView roomNumber;
    private TextView date3;
    private TextView hour3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_class_admin);

        roomNumber = (TextView) findViewById(R.id.roomNumber);
        date3 = (TextView) findViewById(R.id.date3);
        hour3 = (TextView) findViewById(R.id.hour3);
        teacherStar = (TextView) findViewById(R.id.schoolStar1);
        studentsStar = (TextView) findViewById(R.id.capacityStar);
        dateStar = (TextView) findViewById(R.id.dateStar);
        schoolStar = (TextView) findViewById(R.id.schoolStar);
        hourStar = (TextView) findViewById(R.id.hourStar);
        roomStar = (TextView) findViewById(R.id.roomStar);
        descriptionStar = (TextView) findViewById(R.id.floorStar);
        subjectStar = (TextView) findViewById(R.id.numberStar);
        durationStar = (TextView) findViewById(R.id.durationStar);
        ageStar = (TextView) findViewById(R.id.ageStar);
        roomPickerBtn = (Button) findViewById(R.id.roomPickerBtn);
        datePickerBtn = (Button) findViewById(R.id.datePickerBtn);
        hourPickerBtn = (Button) findViewById(R.id.hourPickerBtn);
        arrow5 = (ImageView) findViewById(R.id.arrow5);
        updateBtn = (Button) findViewById(R.id.updateBtn);
        teacherSpinner = (Spinner) findViewById(R.id.teacherSpinner);
        multiAutoCompleteTextView = (MultiAutoCompleteTextView) findViewById(R.id.multiAutoCompleteTextView);
        notesEditText = (EditText) findViewById(R.id.notesEditText);
        subjectSpinner = (Spinner) findViewById(R.id.subjectSpinner);
        durationSpinner = (Spinner) findViewById(R.id.durationSpinner);
        schoolsSpinner = (Spinner) findViewById(R.id.schoolsSpinner);
        ageSpinner = (Spinner) findViewById(R.id.ageSpinner);
        everyWeekCheckBox = (CheckBox) findViewById(R.id.everyWeekCheckBox);
        everyDayCheckBox = (CheckBox) findViewById(R.id.everyDayCheckBox);

        arrow5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(update_class_admin_activity.this, all_classes_activity.class);
                startActivity(intent);
            }
        });

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int check = 1;

                /*if(teacherSpinner.getSelectedItem().toString().equals("")){
                    teacherStar.setVisibility(View.VISIBLE);
                    check = 0;
                }*/
                if(multiAutoCompleteTextView.getText().toString().equals("")){
                    studentsStar.setVisibility(View.VISIBLE);
                    check = 0;
                }
                if (notesEditText.getText().toString().equals("")){
                    descriptionStar.setVisibility(View.VISIBLE);
                    check = 0;
                }
                /*if (schoolsSpinner.getSelectedItem().toString().equals("")){
                    schoolStar.setVisibility(View.VISIBLE);
                    check = 0;
                }*/
                if(roomNumber.getText().toString().equals("")){
                    roomStar.setVisibility(View.VISIBLE);
                    check = 0;
                }
                if(date3.getText().toString().equals("")){
                    dateStar.setVisibility(View.VISIBLE);
                    check = 0;
                }
                if (hour3.getText().toString().equals("")){
                    hourStar.setVisibility(View.VISIBLE);
                    check = 0;
                }
                else if(check == 1) {
                    SysData.getInstance().getClasses().add(new Class(SysData.getInstance().getTeacherByUserName(teacherSpinner.getSelectedItem().toString()), SysData.getInstance().getStudentsByMultiText(multiAutoCompleteTextView.getText().toString()), notesEditText.getText().toString(), SysData.getInstance().getDateByString(date3.getText().toString()), SysData.getInstance().getHourByString(hour3.getText().toString()), SysData.getInstance().getSubjectBySubjectName(subjectSpinner.getSelectedItem().toString()), Integer.parseInt(durationSpinner.getSelectedItem().toString()), ageSpinner.getSelectedItem().toString(), SysData.getInstance().getSchoolByName(schoolsSpinner.getSelectedItem().toString()), SysData.getInstance().getRoomByRoomNumber(roomNumber.getText().toString()), everyWeekCheckBox.isChecked(), everyDayCheckBox.isChecked()));
                    Toast toast = Toast.makeText(getApplicationContext(), "updated successfully", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        roomPickerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createRoomsDialog();

            }
        });


        datePickerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createDateDialog();
            }
        });

        hourPickerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createTimeDialog();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    public void createRoomsDialog(){
        dialogBuilder = new AlertDialog.Builder(this);
        final View roomsDialog = getLayoutInflater().inflate(R.layout.rooms_pop_up, null);

        dialogBuilder.setView(roomsDialog);
        dialog = dialogBuilder.create();
        dialog.show();

    }

    public void createDateDialog(){
        DatePickerDialog dateDialog = new DatePickerDialog(this,R.style.DateDialogTheme, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                date3.setText(String.valueOf(day)+"."+String.valueOf(month)+"."+String.valueOf(year));
            }
        }, 2023, 3, 6);


        dateDialog.show();

    }

    public void createTimeDialog(){
        TimePickerDialog timeDialog = new TimePickerDialog(this,R.style.DateDialogTheme, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hours, int minute) {
                hour3.setText(String.valueOf(hours)+":"+String.valueOf(minute));
            }
        }, 15, 00, true);
        timeDialog.show();
    }
    }
