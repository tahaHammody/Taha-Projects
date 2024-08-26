package com.example.finalproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class home_page_for_student_activity extends Activity {

	
	private TextView student;
	private ImageView imageView2;
	private Button myClassesBtn;
	private ImageView notification;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.home_page_for_student);


		student = (TextView) findViewById(R.id.student);
		imageView2 = (ImageView) findViewById(R.id.imageView2);
		myClassesBtn = (Button) findViewById(R.id.myClassesBtn);
		notification = (ImageView) findViewById(R.id.notification);


		imageView2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(home_page_for_student_activity.this, login_activity.class);
				startActivity(intent);
			}
		});

		myClassesBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(home_page_for_student_activity.this, students_classes_activity.class);
				startActivity(intent);

			}
		});

		notification.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(home_page_for_student_activity.this , student_messages_activity.class);
				startActivity(intent);
			}
		});


	}
}
	
	