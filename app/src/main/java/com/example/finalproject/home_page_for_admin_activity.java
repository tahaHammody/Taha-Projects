package com.example.finalproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class home_page_for_admin_activity extends Activity {


	private ImageView myv;
	private ImageView imageView3;
	private Button classesBtn;
	private Button teachersBtn;
	private Button studentsBtn;
	private Button reviewsBtn;
	private Button SchoolsBtn;
	private Button roomsBtn;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.home_page_for_admin);

		myv = (ImageView) findViewById(R.id.myv);
		imageView3 = (ImageView) findViewById(R.id.imageView3);
		classesBtn = (Button) findViewById(R.id.classesBtn);
		teachersBtn = (Button) findViewById(R.id.teachersBtn);
		studentsBtn = (Button) findViewById(R.id.studentsBtn);
		reviewsBtn = (Button) findViewById(R.id.reviewsBtn);
		SchoolsBtn = (Button) findViewById(R.id.SchoolsBtn);
		roomsBtn = (Button) findViewById(R.id.roomsBtn);
	
		
		imageView3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(home_page_for_admin_activity.this, login_activity.class);
				startActivity(intent);
			}
		});

		classesBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(home_page_for_admin_activity.this, all_classes_activity.class);
				startActivity(intent);
			}
		});

		teachersBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(home_page_for_admin_activity.this, all_teachers_activity.class);
				startActivity(intent);
			}
		});

		studentsBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(home_page_for_admin_activity.this, all_students_activity.class);
				startActivity(intent);
			}
		});

		reviewsBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(home_page_for_admin_activity.this, all_reviews_activity.class);
				startActivity(intent);
			}
		});

		SchoolsBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(home_page_for_admin_activity.this, all_schools_activity.class);
				startActivity(intent);
			}
		});

		roomsBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(home_page_for_admin_activity.this, all_rooms_activity.class);
				startActivity(intent);
			}
		});

		myv.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(home_page_for_admin_activity.this, admin_messages_activity.class);
				startActivity(intent);
			}
		});
	
	}
}
	
	