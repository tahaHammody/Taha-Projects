package com.example.finalproject;

/*
	 *	This content is generated from the API File Info.
	 *	(Alt+Shift+Ctrl+I).
	 *
	 *	@desc 		
	 *	@file 		info
	 *	@date 		Wednesday 15th of March 2023 07:03:46 PM
	 *	@title 		info
	 *	@author 	
	 *	@keywords 	
	 *	@generator 	Export Kit v1.3.figma
	 *
	 */


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class info_activity extends Activity {

	
	private View _bg__info_ek2;
	private ImageView rectangle_1;
	private TextView maryam_khalil;
	private TextView taha_hammody;
	private TextView karam_knanh;
	private TextView and_we_build_this_app_to_help_students_and_teachers_to_find_suitable_classes_in_different_subject_for_learning__studying__motivating__preparing_for_exams__solving_homeworks__and_enjoying_school__;
	private TextView we_are_three_information_systems_students_;
	private ImageView arrow_left_by_streamlinehq;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.info);

		
		_bg__info_ek2 = (View) findViewById(R.id._bg__info_ek2);
		rectangle_1 = (ImageView) findViewById(R.id.rectangle_1);
		maryam_khalil = (TextView) findViewById(R.id.maryam_khalil);
		taha_hammody = (TextView) findViewById(R.id.taha_hammody);
		karam_knanh = (TextView) findViewById(R.id.karam_knanh);
		and_we_build_this_app_to_help_students_and_teachers_to_find_suitable_classes_in_different_subject_for_learning__studying__motivating__preparing_for_exams__solving_homeworks__and_enjoying_school__ = (TextView) findViewById(R.id.and_we_build_this_app_to_help_students_and_teachers_to_find_suitable_classes_in_different_subject_for_learning__studying__motivating__preparing_for_exams__solving_homeworks__and_enjoying_school__);
		we_are_three_information_systems_students_ = (TextView) findViewById(R.id.we_are_three_information_systems_students_);
		arrow_left_by_streamlinehq = (ImageView) findViewById(R.id.arrow_left_by_streamlinehq);
	
		
		arrow_left_by_streamlinehq.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(info_activity.this, WelcomeActivity.class);
				startActivity(intent);
			}
		});
	
	}
}
	
	