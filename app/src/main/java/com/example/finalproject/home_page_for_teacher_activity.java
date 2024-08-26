package com.example.finalproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class home_page_for_teacher_activity extends Activity {


	private TextView myteacher;
	private ImageView notification;
	private ImageView imageView6;
	private Button myClassesBtn;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.home_page_for_teacher);

		myteacher = (TextView) findViewById(R.id.myteacher);
		imageView6 = (ImageView) findViewById(R.id.imageView6);
		myClassesBtn = (Button) findViewById(R.id.myClassesBtn);
		notification = (ImageView) findViewById(R.id.notification);


		imageView6.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(home_page_for_teacher_activity.this, login_activity.class);
				startActivity(intent);
			}
		});

		myClassesBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(home_page_for_teacher_activity.this , teacher_classes_activity.class);
				startActivity(intent);
			}
		});
		notification.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(home_page_for_teacher_activity.this , teacher_messages_activity.class);
				startActivity(intent);
			}
		});


	}
}
	
	