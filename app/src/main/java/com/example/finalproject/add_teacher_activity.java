package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import Logic.Class;
import Logic.Enums.Type;
import Logic.SysData;
import Logic.Teacher;

public class add_teacher_activity extends AppCompatActivity {

    private ImageView arrow6;
    private Button addButton2;
    private EditText UserNameEditText;
    private EditText EmailEditText;
    private EditText PhoneEditText;
    private EditText passwordEditText;
    private Spinner AddressSpinner;
    private Spinner subjectSpinner6;
    private Spinner senioritySpinner;
    private Spinner genderSpinner;
    private EditText ageEditText;
    private TextView phoneStar;
    private TextView passwordStar;
    private TextView ageStar;
    private TextView userNameStar;
    private TextView emailStar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_teacher);

        arrow6 = (ImageView) findViewById(R.id.arrow6);
        addButton2 = (Button) findViewById(R.id.addButton2);
        UserNameEditText = (EditText) findViewById(R.id.UserNameEditText);
        EmailEditText = (EditText) findViewById(R.id.EmailEditText);
        PhoneEditText = (EditText) findViewById(R.id.PhoneEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);
        AddressSpinner = (Spinner) findViewById(R.id.AddressSpinner);
        subjectSpinner6 = (Spinner) findViewById(R.id.subjectSpinner6);
        senioritySpinner = (Spinner) findViewById(R.id.senioritySpinner);
        genderSpinner = (Spinner) findViewById(R.id.genderSpinner);
        ageEditText = (EditText) findViewById(R.id.ageEditText);
        phoneStar = (TextView) findViewById(R.id.phoneStar);
        passwordStar = (TextView) findViewById(R.id.passwordStar);
        ageStar = (TextView) findViewById(R.id.ageStar);
        userNameStar = (TextView) findViewById(R.id.userNameStar);
        emailStar = (TextView) findViewById(R.id.emailStar);

        arrow6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(add_teacher_activity.this, all_teachers_activity.class);
                startActivity(intent);
            }
        });
        addButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int check = 1;

                if(UserNameEditText.getText().toString().equals("")){
                    userNameStar.setVisibility(View.VISIBLE);
                    check = 0;
                }
                if(EmailEditText.getText().toString().equals("")){
                    emailStar.setVisibility(View.VISIBLE);
                    check = 0;
                }
                if(PhoneEditText.getText().toString().equals("")){
                    phoneStar.setVisibility(View.VISIBLE);
                    check = 0;
                }
                if(passwordEditText.getText().toString().equals("")){
                    passwordStar.setVisibility(View.VISIBLE);
                    check = 0;
                }
                if(ageEditText.getText().toString().equals("")){
                    ageStar.setVisibility(View.VISIBLE);
                    check = 0;
                }
                else if(check == 1) {
                    SysData.getInstance().getTeachers().add(new Teacher(UserNameEditText.getText().toString(), EmailEditText.getText().toString(), PhoneEditText.getText().toString(), passwordEditText.getText().toString(), SysData.getInstance().getRegionByRegionName(AddressSpinner.getSelectedItem().toString()), Type.Teacher, R.drawable.female_woman_person_people_avatar_user_white_tone_icon_159359, SysData.getInstance().getGenderByGenderName(genderSpinner.getSelectedItem().toString()), ageEditText.getText().toString(), SysData.getInstance().getSubjectBySubjectName(subjectSpinner6.getSelectedItem().toString()), Integer.parseInt(senioritySpinner.getSelectedItem().toString())));
                    Toast toast = Toast.makeText(getApplicationContext(), "added successfully", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
    }
}