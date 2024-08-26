package com.example.finalproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.auth.FirebaseAuth;

public class sign_up_activity extends Activity {


	private ImageView vector;
	private ImageView vector_ek1;
	private ImageView arrow_left_by_streamlinehq_ek1;
	private ImageView __img___codicon_account;
	private Button sign_up1;
	private TextView insertYourUserNameMessage;
	private TextView insertYourPhoneMessage;
	private TextView EmailMessage;
	private TextView passwordMessage;
	private TextView rePasswordMessage;
	private TextView genderMessage;
	private TextView ageMessage;

	private EditText editTextPhone;
	private EditText userNameEditText1;
	private EditText emailEditText;
	private Spinner addressSpinner5;
	private EditText ageEditText;
	private EditText passwordEditText1;
	private EditText rePasswordEditText;
	private CheckBox male;
	private CheckBox female;


	private FirebaseAuth auth;


	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.sign_up);
       auth=FirebaseAuth.getInstance();

		arrow_left_by_streamlinehq_ek1 = (ImageView) findViewById(R.id.arrow_left_by_streamlinehq_ek1);
		__img___codicon_account = (ImageView) findViewById(R.id.__img___codicon_account);
		sign_up1 = (Button) findViewById(R.id.sign_up1);
		vector_ek1 = (ImageView)  findViewById(R.id.vector_ek1);
		vector = (ImageView)  findViewById(R.id.vector);
		insertYourUserNameMessage = (TextView) findViewById(R.id.insertYourUserNameMessage) ;
		insertYourPhoneMessage = (TextView) findViewById(R.id.insertYourPhoneMessage);
		EmailMessage = (TextView) findViewById(R.id.EmailMessage);
		passwordMessage = (TextView) findViewById(R.id.passwordMessage);
		rePasswordMessage = (TextView) findViewById(R.id.rePasswordMessage) ;
		genderMessage = (TextView) findViewById(R.id.genderMessage);
		ageMessage = (TextView) findViewById(R.id.ageMessage);
		editTextPhone = (EditText) findViewById(R.id.editTextPhone);
		userNameEditText1 = (EditText) findViewById(R.id.userNameEditText1);
		emailEditText = (EditText) findViewById(R.id.emailEditText);
		addressSpinner5 = (Spinner) findViewById(R.id.addressSpinner5);
		ageEditText = (EditText) findViewById(R.id.ageEditText);
		passwordEditText1 = (EditText) findViewById(R.id.passwordEditText1);
		rePasswordEditText = (EditText) findViewById(R.id.rePasswordEditText);
		male = (CheckBox) findViewById(R.id.male);
		female = (CheckBox) findViewById(R.id.female);



		sign_up1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				int check = 1;

				if(userNameEditText1.getText().toString().equals("")){
					insertYourUserNameMessage.setVisibility(View.VISIBLE);
					check = 0;
				}
				if(editTextPhone.getText().toString().equals("")){
					insertYourPhoneMessage.setVisibility(View.VISIBLE);
					check = 0;
				}
				if(emailEditText.getText().toString().equals("")){
					EmailMessage.setVisibility(View.VISIBLE);
					check = 0;
				}
				if(ageEditText.getText().toString().equals("")){
					ageMessage.setVisibility(View.VISIBLE);
					check = 0;
				}
				if(passwordEditText1.getText().toString().equals("")){
					passwordMessage.setVisibility(View.VISIBLE);
					check = 0;
				}
				if(rePasswordEditText.getText().toString().equals("")){
					rePasswordMessage.setVisibility(View.VISIBLE);
					check = 0;
				}
				if(!male.isChecked() && !female.isChecked()){
					genderMessage.setVisibility(View.VISIBLE);
					check = 0;
				}
				if(!passwordEditText1.getText().toString().equals(rePasswordEditText.getText().toString())){
					Toast.makeText(getApplicationContext(), "you have to re enter your password correctly!",Toast.LENGTH_LONG).show();
					check = 0;
				}

				else if(check == 1) {
					registerUser(emailEditText.getText().toString(),passwordEditText1.getText().toString());
					Intent intent = new Intent(sign_up_activity.this, login_activity.class);
					startActivity(intent);
				}
			}
		});
		vector.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (rePasswordEditText.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())){
					rePasswordEditText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
				}
				else{
					rePasswordEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());
				}
			}
		});
		vector_ek1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (passwordEditText1.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())){
					passwordEditText1.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
				}
				else{
					passwordEditText1.setTransformationMethod(PasswordTransformationMethod.getInstance());
				}
			}
		});
		arrow_left_by_streamlinehq_ek1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(sign_up_activity.this, login_activity.class);
				startActivity(intent);
			}
		});
	
	}
	private void registerUser(String email,String password){
		auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(sign_up_activity.this , task -> {
			if(task.isSuccessful()){
				Toast.makeText(sign_up_activity.this,"successfull!!!!!",Toast.LENGTH_SHORT).show();
			}else{
				Toast.makeText(sign_up_activity.this,"Faild------",Toast.LENGTH_SHORT).show();

			}
		});
	}
}
	
	