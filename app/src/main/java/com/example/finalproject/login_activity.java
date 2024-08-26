package com.example.finalproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.SignInMethodQueryResult;


public class login_activity extends Activity {
	public interface OnEmailCheckListener {
		void onSuccess(boolean isRegistered);
	}

	private Button log_in;
	private ImageView vector;
	private TextView forgotten_password__;
	private TextView don_t_have_an_accoount__sign_up;
	private ImageView arrow_left_by_streamlinehq_ek1;
	private EditText editTextTextEmailAddress;
	private EditText editTextTextPassword;
	private TextView textView;
	private TextView textView2;
	private FirebaseAuth auth=FirebaseAuth.getInstance();


	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);

		log_in = (Button) findViewById(R.id.send);
		vector = (ImageView) findViewById(R.id.vector);
		forgotten_password__ = (TextView) findViewById(R.id.forgotten_password__);
		don_t_have_an_accoount__sign_up = (TextView) findViewById(R.id.don_t_have_an_accoount__sign_up);
		arrow_left_by_streamlinehq_ek1 = (ImageView) findViewById(R.id.arrow_left_by_streamlinehq_ek1);
		editTextTextPassword = (EditText) findViewById(R.id.editTextTextPassword);
		editTextTextEmailAddress = (EditText) findViewById(R.id.editTextTextEmailAddress);
		textView = (TextView) findViewById(R.id.textView) ;
		textView2 = (TextView) findViewById(R.id.textView2) ;




		arrow_left_by_streamlinehq_ek1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(login_activity.this, WelcomeActivity.class);
				startActivity(intent);
			}
		});

		don_t_have_an_accoount__sign_up.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(login_activity.this, sign_up_activity.class);
				startActivity(intent);
			}
		});

		log_in.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				int check = 1;
				isCheckEmail((editTextTextEmailAddress.getText().toString()));
				if(editTextTextEmailAddress.getText().toString().equals("")){
					textView.setVisibility(View.VISIBLE);
					check = 0;
				}
				if(editTextTextPassword.getText().toString().equals("")){
					textView2.setVisibility(View.VISIBLE);
					check = 0;
				}

				else if(check == 1){
					if(editTextTextEmailAddress.getText().toString().equals("admin") && editTextTextPassword.getText().toString().equals("admin")) {
						Intent intent = new Intent(login_activity.this, home_page_for_admin_activity.class);
						startActivity(intent);
					}
					else if(editTextTextEmailAddress.getText().toString().equals("student") && editTextTextPassword.getText().toString().equals("student")){
						Intent intent = new Intent(login_activity.this, home_page_for_student_activity.class);
						startActivity(intent);
					}
					else if(editTextTextEmailAddress.getText().toString().equals("teacher") && editTextTextPassword.getText().toString().equals("teacher")){
						Intent intent = new Intent(login_activity.this, home_page_for_teacher_activity.class);
						startActivity(intent);
					}
					else{
						Intent intent = new Intent(login_activity.this, home_page_for_student_activity.class);
						startActivity(intent);
					}
				}
				}

		});
		vector.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (editTextTextPassword.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())){
					editTextTextPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
			}
				else{
					editTextTextPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
				}
			}
		});
		forgotten_password__.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(login_activity.this, forget_Password_Activity.class);
				startActivity(intent);
			}
		});
	}
	public void isCheckEmail(String v){
		auth.fetchSignInMethodsForEmail(v)
				.addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
										@Override
					public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {
						boolean check =!task.getResult().getSignInMethods().isEmpty();
						if (check)
							Toast.makeText(login_activity.this,"Failddddddddddd!!!!!",Toast.LENGTH_SHORT).show();

						else
							Toast.makeText(login_activity.this,"successful!!!!!",Toast.LENGTH_SHORT).show();

										}
				});
	}
}


