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

import org.w3c.dom.Text;

import Logic.Class;
import Logic.Enums.Type;
import Logic.Student;
import Logic.SysData;

public class add_student_activity extends AppCompatActivity {

    private ImageView arrow7;
    private Button addButton3;
    private EditText studentUserNameEditText;
    private EditText studentMailEditText;
    private EditText StudentPhoneEditText;
    private EditText StudentPasswordEditText;
    private Spinner StudentAddressSpinner;
    private Spinner genderSpinner;
    private Spinner ageSpinner2;
    private TextView userNameStar;
    private TextView emailStar;
    private TextView phoneStar;
    private TextView passwordStar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_student);

        arrow7 = (ImageView) findViewById(R.id.arrow7);
        addButton3 = (Button) findViewById(R.id.addButton3);
        studentUserNameEditText = (EditText) findViewById(R.id.studentUserNameEditText);
        studentMailEditText = (EditText) findViewById(R.id.studentMailEditText);
        StudentPhoneEditText = (EditText) findViewById(R.id.StudentPhoneEditText);
        StudentPasswordEditText = (EditText) findViewById(R.id.StudentPasswordEditText);
        StudentAddressSpinner = (Spinner) findViewById(R.id.StudentAddressSpinner);
        genderSpinner = (Spinner) findViewById(R.id.genderSpinner);
        ageSpinner2 = (Spinner) findViewById(R.id.ageSpinner2);
        userNameStar = (TextView) findViewById(R.id.userNameStar);
        emailStar = (TextView) findViewById(R.id.emailStar);
        phoneStar = (TextView) findViewById(R.id.phoneStar);
        passwordStar = (TextView) findViewById(R.id.passwordStar);

        arrow7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(add_student_activity.this, all_students_activity.class);
                startActivity(intent);
            }
        });

        addButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int check = 1;

                if(studentUserNameEditText.getText().toString().equals("")){
                    userNameStar.setVisibility(View.VISIBLE);
                    check = 0;
                }
                if(studentMailEditText.getText().toString().equals("")){
                    emailStar.setVisibility(View.VISIBLE);
                    check = 0;
                }
                if(StudentPhoneEditText.getText().toString().equals("")){
                    phoneStar.setVisibility(View.VISIBLE);
                    check = 0;
                }
                if(StudentPasswordEditText.getText().toString().equals("")){
                    passwordStar.setVisibility(View.VISIBLE);
                    check = 0;
                }
                else if(check == 1) {
                    SysData.getInstance().getStudents().add(new Student(studentUserNameEditText.getText().toString(), studentMailEditText.getText().toString(), StudentPhoneEditText.getText().toString(), StudentPasswordEditText.getText().toString(),SysData.getInstance().getRegionByRegionName(StudentAddressSpinner.getSelectedItem().toString()), Type.Student, R.drawable.male_boy_person_people_avatar_white_tone_icon_159368, SysData.getInstance().getGenderByGenderName(genderSpinner.getSelectedItem().toString()), ageSpinner2.getSelectedItem().toString()));
                    Toast toast = Toast.makeText(getApplicationContext(), "added successfully", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
    }
}