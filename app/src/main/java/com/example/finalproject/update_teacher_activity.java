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
import Logic.SysData;
import Logic.Teacher;

public class update_teacher_activity extends AppCompatActivity {

    private ImageView arrow6;
    private Button updateBtn4;
    private EditText UserNameEditText;
    private EditText EmailEditText;
    private EditText PhoneEditText;
    private EditText passwordEditText;
    private Spinner AddressSpinner;
    private Spinner subjectSpinner6;
    private Spinner senioritySpinner;
    private Spinner genderSpinner;
    private EditText ageEditText;
    private TextView userNameStar;
    private TextView emailStar;
    private TextView phoneStar;
    private TextView passwordStar;
    private TextView ageStar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_teacher);

        arrow6 = (ImageView) findViewById(R.id.arrow6);
        updateBtn4 = (Button) findViewById(R.id.updateBtn4);
        UserNameEditText = (EditText) findViewById(R.id.UserNameEditText);
        EmailEditText = (EditText) findViewById(R.id.EmailEditText);
        PhoneEditText = (EditText) findViewById(R.id.PhoneEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);
        AddressSpinner = (Spinner) findViewById(R.id.AddressSpinner);
        subjectSpinner6 = (Spinner) findViewById(R.id.subjectSpinner6);
        senioritySpinner = (Spinner) findViewById(R.id.senioritySpinner);
        genderSpinner = (Spinner) findViewById(R.id.genderSpinner);
        ageEditText = (EditText) findViewById(R.id.ageEditText);
        userNameStar = (TextView) findViewById(R.id.userNameStar);
        emailStar = (TextView) findViewById(R.id.emailStar);
        phoneStar = (TextView) findViewById(R.id.phoneStar);
        passwordStar = (TextView) findViewById(R.id.passwordStar);
        ageStar = (TextView) findViewById(R.id.ageStar);

        arrow6.setOnClickListener(v -> {
            Intent intent = new Intent(update_teacher_activity.this, all_teachers_activity.class);
            startActivity(intent);
        });

        updateBtn4.setOnClickListener(v -> {
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
                Toast toast = Toast.makeText(getApplicationContext(), "updated successfully", Toast.LENGTH_SHORT);
                toast.show();
            }

        });


    }
}