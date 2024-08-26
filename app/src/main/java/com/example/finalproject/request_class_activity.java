package com.example.finalproject;

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
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

import Logic.Message;
import Logic.School;
import Logic.SysData;
import Logic.User;

public class request_class_activity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private ImageView arrow8;
    private Button requestButton;
    private Spinner subjectSpinner;
    private Spinner ageSpinner;
    private Spinner schoolsSpinner;
    private Button pickingDateBtn;
    private Button pickingHourBtn;
    private CheckBox everyWeekCheckBox;
    private CheckBox everyDayCheckBox;
    private TextView datePicked;
    private TextView hourPicked;
    private TextView hourStar7;
    private TextView dateStar;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.request_class);

        arrow8 = (ImageView) findViewById(R.id.arrow8);
        requestButton = (Button) findViewById(R.id.requestButton);
        subjectSpinner = (Spinner) findViewById(R.id.subjectSpinner);
        ageSpinner = (Spinner) findViewById(R.id.ageSpinner);
        schoolsSpinner = (Spinner) findViewById(R.id.schoolsSpinner);
        pickingDateBtn = (Button) findViewById(R.id.pickingDateBtn);
        pickingHourBtn = (Button) findViewById(R.id.pickingHourBtn);
        everyWeekCheckBox = (CheckBox) findViewById(R.id.everyWeekCheckBox);
        everyDayCheckBox = (CheckBox) findViewById(R.id.everyDayCheckBox);
        datePicked = (TextView) findViewById(R.id.datePicked);
        hourPicked = (TextView) findViewById(R.id.hourPicked);
        hourStar7 = (TextView) findViewById(R.id.hourStar7);
        dateStar = (TextView) findViewById(R.id.dateStar);


        arrow8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(request_class_activity.this, add_class_student_activity.class);
                startActivity(intent);
            }
        });

        requestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int check = 1;

                if(datePicked.getText().toString().equals("")){
                    dateStar.setVisibility(View.VISIBLE);
                    check = 0;
                }
                if(hourPicked.getText().toString().equals("")){
                    hourStar7.setVisibility(View.VISIBLE);
                    check = 0;
                }
                else if(check == 1){
                    ArrayList<Message> adminMessages = new ArrayList<Message>();
                    User admin = new User("admin", 0);
                    adminMessages.add(new Message(user, admin, new Date(), "request class", getContent()));
                    SysData.getInstance().getUserMessages().put(admin, adminMessages);
                    Toast toast = Toast.makeText(getApplicationContext(), "your request sent successfully", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        pickingDateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createDateDialog();
            }
        });

        pickingHourBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createTimeDialog();
            }
        });
    }

    private String getContent() {
        return "Class Attributes : \n" +
                "Subject : " + subjectSpinner.getSelectedItem().toString() + "\n" +
                "Age : " + ageSpinner.getSelectedItem().toString() + "\n" +
                "School : " + schoolsSpinner.getSelectedItem().toString() + "\n" +
                "Date : " + datePicked.getText().toString() + "\n" +
                "Hour : " + hourPicked.getText().toString() + "\n" +
                "Every Day : " + everyDayCheckBox.isChecked() + "\n" +
                "Every Week : " + everyWeekCheckBox.isChecked() + "\n";
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void createDateDialog(){
        DatePickerDialog dateDialog = new DatePickerDialog(this,R.style.DateDialogTheme, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                datePicked.setText(String.valueOf(day)+"."+String.valueOf(month)+"."+String.valueOf(year));
            }
        }, 2023, 3, 6);


        dateDialog.show();

    }

    public void createTimeDialog(){
        TimePickerDialog timeDialog = new TimePickerDialog(this,R.style.DateDialogTheme, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hours, int minute) {
                hourPicked.setText(String.valueOf(hours)+":"+String.valueOf(minute));
            }
        }, 15, 00, true);
        timeDialog.show();
    }
}