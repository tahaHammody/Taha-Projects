package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class forget_Password_Activity extends AppCompatActivity {

    private ImageView arrow_left_by_streamlinehq_ek1;
    private Button send;
    private EditText userNameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forget_password);

        arrow_left_by_streamlinehq_ek1 = (ImageView) findViewById(R.id.arrow_left_by_streamlinehq_ek1);
        send = (Button) findViewById(R.id.send);
        userNameEditText = (EditText) findViewById(R.id.userNameEditText);


        arrow_left_by_streamlinehq_ek1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(forget_Password_Activity.this, login_activity.class);
                startActivity(intent);
            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}