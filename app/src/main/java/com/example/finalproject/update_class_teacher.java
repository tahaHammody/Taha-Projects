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
import Logic.Teacher;

public class update_class_teacher extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private ImageView arrow13;
    private Button updateBtn;
    private Button roomPickerBtn;
    private Button datePickerBtn;
    private Button hourPickerBtn;
    private MultiAutoCompleteTextView multiAutoCompleteTextView;
    private EditText descriptionEditText;
    private Spinner subjectSpinner;
    private Spinner durationSpinner;
    private Spinner ageSpinner;
    private Spinner schoolsSpinner;
    private CheckBox everyWeekCheckBox;
    private CheckBox everyDayCheckBox;
    private TextView studentsStar;
    private TextView descriptionStar;
    private TextView dateStar;
    private TextView hourStar;
    private TextView roomStar;
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private TextView roomNumber;
    private TextView date3;
    private TextView hour3;
    private Teacher teacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_class_teacher);

        arrow13 = (ImageView) findViewById(R.id.arrow13);
        roomNumber = (TextView) findViewById(R.id.roomNumber);
        date3 = (TextView) findViewById(R.id.date3);
        hour3 = (TextView) findViewById(R.id.hour3);
        dateStar = (TextView) findViewById(R.id.dateStar);
        hourStar = (TextView) findViewById(R.id.hourStar);
        roomStar = (TextView) findViewById(R.id.roomStar);
        updateBtn = (Button) findViewById(R.id.updateBtn);
        roomPickerBtn = (Button) findViewById(R.id.roomPickerBtn);
        datePickerBtn = (Button) findViewById(R.id.datePickerBtn);
        hourPickerBtn = (Button) findViewById(R.id.hourPickerBtn);
        multiAutoCompleteTextView = (MultiAutoCompleteTextView) findViewById(R.id.multiAutoCompleteTextView);
        descriptionEditText = (EditText) findViewById(R.id.descriptionEditText);
        subjectSpinner = (Spinner) findViewById(R.id.subjectSpinner);
        durationSpinner = (Spinner) findViewById(R.id.durationSpinner);
        ageSpinner = (Spinner) findViewById(R.id.ageSpinner);
        schoolsSpinner = (Spinner) findViewById(R.id.schoolsSpinner);
        everyWeekCheckBox = (CheckBox) findViewById(R.id.everyWeekCheckBox);
        everyDayCheckBox = (CheckBox) findViewById(R.id.everyDayCheckBox);
        studentsStar = (TextView) findViewById(R.id.studentsStar);
        descriptionStar = (TextView) findViewById(R.id.descriptionStar);

        arrow13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(update_class_teacher.this, teacher_classes_activity.class);
                startActivity(intent);
            }
        });

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int check = 1;

                if(multiAutoCompleteTextView.getText().toString().equals("")){
                    studentsStar.setVisibility(View.VISIBLE);
                    check = 0;
                }
                if(descriptionEditText.getText().toString().equals("")){
                    descriptionStar.setVisibility(View.VISIBLE);
                    check = 0;
                }
                if(roomNumber.getText().toString().equals("")){
                    roomStar.setVisibility(View.VISIBLE);
                    check = 0;
                }
                if(date3.getText().toString().equals("")){
                    dateStar.setVisibility(View.VISIBLE);
                    check = 0;
                }
                if(hour3.getText().toString().equals("")){
                    hourStar.setVisibility(View.VISIBLE);
                    check = 0;
                }
                else if(check == 1){
                    SysData.getInstance().getClasses().add(new Class(teacher, SysData.getInstance().getStudentsByMultiText(multiAutoCompleteTextView.getText().toString()), descriptionEditText.getText().toString(), SysData.getInstance().getDateByString(date3.getText().toString()), SysData.getInstance().getHourByString(hour3.getText().toString()), SysData.getInstance().getSubjectBySubjectName(subjectSpinner.getSelectedItem().toString()), Integer.parseInt(durationSpinner.getSelectedItem().toString()), ageSpinner.getSelectedItem().toString(), SysData.getInstance().getSchoolByName(schoolsSpinner.getSelectedItem().toString()), SysData.getInstance().getRoomByRoomNumber(roomNumber.getText().toString()), everyWeekCheckBox.isChecked(), everyDayCheckBox.isChecked()));
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

    public void createRoomsDialog() {
        dialogBuilder = new AlertDialog.Builder(this);
        final View roomsDialog = getLayoutInflater().inflate(R.layout.rooms_pop_up, null);

        dialogBuilder.setView(roomsDialog);
        dialog = dialogBuilder.create();
        dialog.show();

    }

    public void createDateDialog() {
        DatePickerDialog dateDialog = new DatePickerDialog(this, R.style.DateDialogTheme, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                date3.setText(String.valueOf(day) + "." + String.valueOf(month) + "." + String.valueOf(year));
            }
        }, 2023, 3, 6);


        dateDialog.show();

    }

    public void createTimeDialog() {
        TimePickerDialog timeDialog = new TimePickerDialog(this, R.style.DateDialogTheme, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hours, int minute) {
                hour3.setText(String.valueOf(hours) + ":" + String.valueOf(minute));
            }
        }, 15, 00, true);
        timeDialog.show();

    }
}