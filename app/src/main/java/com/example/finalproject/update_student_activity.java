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

import Logic.Enums.Type;
import Logic.Student;
import Logic.SysData;

public class update_student_activity extends AppCompatActivity {

    private ImageView arrow7;
    private Button updateBtn3;
    private EditText studentUserNameEditText;
    private EditText studentMailEditText;
    private EditText StudentPhoneEditText;
    private EditText StudentPasswordEditText;
    private Spinner StudentAddressSpinner;
    private Spinner genderSpinner;
    private Spinner ageSpinner;
    private TextView userNameStar;
    private TextView emailStar1;
    private TextView phoneStar1;
    private TextView passwordStar1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_student);

        arrow7 = (ImageView) findViewById(R.id.arrow7);
        updateBtn3 = (Button) findViewById(R.id.updateBtn3);
        studentUserNameEditText = (EditText) findViewById(R.id.studentUserNameEditText);
        studentMailEditText = (EditText) findViewById(R.id.studentMailEditText);
        StudentPhoneEditText = (EditText) findViewById(R.id.StudentPhoneEditText);
        StudentPasswordEditText = (EditText) findViewById(R.id.StudentPasswordEditText);
        StudentAddressSpinner = (Spinner) findViewById(R.id.StudentAddressSpinner);
        genderSpinner = (Spinner) findViewById(R.id.genderSpinner);
        ageSpinner = (Spinner) findViewById(R.id.ageSpinner);
        userNameStar = (TextView) findViewById(R.id.userNameStar);
        emailStar1 = (TextView) findViewById(R.id.emailStar1);
        phoneStar1 = (TextView) findViewById(R.id.phoneStar1);
        passwordStar1 = (TextView) findViewById(R.id.passwordStar1);


        arrow7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(update_student_activity.this, all_students_activity.class);
                startActivity(intent);
            }
        });

        updateBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int check = 1;

                if(studentUserNameEditText.getText().toString().equals("")){
                    userNameStar.setVisibility(View.VISIBLE);
                    check = 0;
                }
                if(studentMailEditText.getText().toString().equals("")){
                    emailStar1.setVisibility(View.VISIBLE);
                    check = 0;
                }
                if(StudentPhoneEditText.getText().toString().equals("")){
                    phoneStar1.setVisibility(View.VISIBLE);
                    check = 0;
                }
                if (StudentPasswordEditText.getText().toString().equals("")){
                    passwordStar1.setVisibility(View.VISIBLE);
                    check = 0;
                }
                else if(check == 1){
                    SysData.getInstance().getStudents().add(new Student(studentUserNameEditText.getText().toString(), studentMailEditText.getText().toString(), StudentPhoneEditText.getText().toString(), StudentPasswordEditText.getText().toString(), SysData.getInstance().getRegionByRegionName(StudentAddressSpinner.getSelectedItem().toString()), Type.Student, 0, SysData.getInstance().getGenderByGenderName(genderSpinner.getSelectedItem().toString()), ageSpinner.getSelectedItem().toString()));
                    Toast toast = Toast.makeText(getApplicationContext(), "updated successfully", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
    }
}