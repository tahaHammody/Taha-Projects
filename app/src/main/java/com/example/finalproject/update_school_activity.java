package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.time.DayOfWeek;
import java.util.HashMap;

import Logic.School;
import Logic.SysData;

public class update_school_activity extends AppCompatActivity {

    private ImageView arrow5;
    private EditText nameEditText;
    private EditText descriptionEditText;
    private Spinner regionSpinner;
    private Button openingHourBtn;
    private Button closingHourBtn;
    private Button updateBtn;
    private CheckBox sunday;
    private CheckBox monday;
    private CheckBox tuesday;
    private CheckBox wednesday;
    private CheckBox thursday;
    private CheckBox friday;
    private CheckBox saturday;
    private TextView nameStar;
    private TextView descriptionStar;
    private TextView openingHourStar;
    private TextView closingHourStar;
    private TextView pickedClosingHour;
    private TextView pickedOpeningHour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_school);

        arrow5 = (ImageView) findViewById(R.id.arrow5);
        nameEditText = (EditText) findViewById(R.id.nameEditText);
        descriptionEditText = (EditText) findViewById(R.id.descriptionEditText);
        regionSpinner = (Spinner) findViewById(R.id.regionSpinner);
        openingHourBtn = (Button) findViewById(R.id.openingHourBtn);
        closingHourBtn = (Button) findViewById(R.id.closingHourBtn);
        updateBtn = (Button) findViewById(R.id.updateBtn);
        sunday = (CheckBox) findViewById(R.id.sunday);
        monday = (CheckBox) findViewById(R.id.monday);
        tuesday = (CheckBox) findViewById(R.id.tuesday);
        wednesday = (CheckBox) findViewById(R.id.wednesday);
        thursday = (CheckBox) findViewById(R.id.thursday);
        friday = (CheckBox) findViewById(R.id.friday);
        saturday = (CheckBox) findViewById(R.id.saturday);
        nameStar = (TextView) findViewById(R.id.nameStar);
        descriptionStar = (TextView) findViewById(R.id.descriptionStar);
        openingHourStar = (TextView) findViewById(R.id.openingHourStar);
        closingHourStar = (TextView) findViewById(R.id.closingHourStar);
        pickedClosingHour = (TextView) findViewById(R.id.pickedClosingHour);
        pickedOpeningHour = (TextView) findViewById(R.id.pickedOpeningHour);

        arrow5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(update_school_activity.this, all_schools_activity.class);
                startActivity(intent);
            }
        });

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<DayOfWeek, Boolean> openingDays = new HashMap<DayOfWeek, Boolean>();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    openingDays.put(DayOfWeek.SUNDAY, sunday.isChecked());
                    openingDays.put(DayOfWeek.MONDAY, monday.isChecked());
                    openingDays.put(DayOfWeek.TUESDAY, tuesday.isChecked());
                    openingDays.put(DayOfWeek.WEDNESDAY, wednesday.isChecked());
                    openingDays.put(DayOfWeek.THURSDAY, thursday.isChecked());
                    openingDays.put(DayOfWeek.FRIDAY, friday.isChecked());
                    openingDays.put(DayOfWeek.SATURDAY, saturday.isChecked());
                }
                int check = 1;

                if(nameEditText.getText().toString().equals("")){
                    nameStar.setVisibility(View.VISIBLE);
                    check = 0;
                }
                if(descriptionEditText.getText().toString().equals("")){
                    descriptionStar.setVisibility(View.VISIBLE);
                    check = 0;
                }
                if(pickedOpeningHour.getText().toString().equals("")){
                    openingHourStar.setVisibility(View.VISIBLE);
                    check = 0;
                }
                if(pickedClosingHour.getText().toString().equals("")){
                    closingHourStar.setVisibility(View.VISIBLE);
                    check = 0;
                }
                else if(check == 1){
                    SysData.getInstance().getSchools().add(new School(descriptionEditText.getText().toString(), nameEditText.getText().toString(), SysData.getInstance().getRegionByRegionName(regionSpinner.getSelectedItem().toString()),  SysData.getInstance().getHourByText(pickedOpeningHour.getText().toString()), SysData.getInstance().getHourByText(pickedClosingHour.getText().toString()),openingDays,null));
                    Toast toast = Toast.makeText(getApplicationContext(), "updated successfully", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });


        openingHourBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createOpeningHourDialog();

            }
        });

        closingHourBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createClosingHourDialog();
            }
        });


    }


    public void createOpeningHourDialog(){
        TimePickerDialog timeDialog = new TimePickerDialog(this,R.style.DateDialogTheme, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hours, int minute) {
                pickedOpeningHour.setText(String.valueOf(hours)+":"+String.valueOf(minute));
            }
        }, 15, 00, true);
        timeDialog.show();
    }
    public void createClosingHourDialog(){
        TimePickerDialog timeDialog = new TimePickerDialog(this,R.style.DateDialogTheme, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hours, int minute) {
                pickedClosingHour.setText(String.valueOf(hours)+":"+String.valueOf(minute));
            }
        }, 15, 00, true);
        timeDialog.show();
    }
}