package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import Logic.Message;
import Logic.SysData;
import Logic.User;

public class send_message_activity extends AppCompatActivity {

    private ImageView arrow_left_by_streamlinehq;
    private Button sendButton3;
    private MultiAutoCompleteTextView toEditText;
    private EditText labelEditText;
    private EditText contentEditText;
    private TextView senderStar;
    private TextView titleStar;
    private TextView contentStar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send_message);

        arrow_left_by_streamlinehq = (ImageView) findViewById(R.id.arrow_left_by_streamlinehq);
        sendButton3 = (Button) findViewById(R.id.sendButton3);
        toEditText = (MultiAutoCompleteTextView) findViewById(R.id.toEditText);
        labelEditText = (EditText) findViewById(R.id.labelEditText);
        contentEditText = (EditText) findViewById(R.id.contentEditText);
        senderStar = (TextView) findViewById(R.id.senderStar);
        titleStar = (TextView) findViewById(R.id.titleStar);
        contentStar = (TextView) findViewById(R.id.contentStar);

        arrow_left_by_streamlinehq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(send_message_activity.this, admin_messages_activity.class);
                startActivity(intent);
            }
        });

        sendButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int check = 1;

                if(toEditText.getText().toString().equals("")){
                    senderStar.setVisibility(View.VISIBLE);
                    check = 0;
                }
                if(labelEditText.getText().toString().equals("")){
                    titleStar.setVisibility(View.VISIBLE);
                    check = 0;
                }
                if(contentEditText.getText().toString().equals("")){
                    contentStar.setVisibility(View.VISIBLE);
                    check = 0;
                }
                else if(check == 1){
                    SysData.getInstance().getMessages().add(new Message(new User(toEditText.getText().toString()), labelEditText.getText().toString(), contentEditText.getText().toString()));
                    Toast toast = Toast.makeText(getApplicationContext(), "sent successfully", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
    }
}