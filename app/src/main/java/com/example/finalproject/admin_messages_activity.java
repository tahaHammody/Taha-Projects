package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import Logic.Message;
import Logic.SysData;
import Logic.User;

public class admin_messages_activity extends AppCompatActivity {

    private ImageView arrow_left_by_streamlinehq;
    private Button sendBtn4;
    private RecyclerView adminMessagesRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_messages);

        List<Message> messages = SysData.getInstance().getMessages();

        arrow_left_by_streamlinehq = (ImageView) findViewById(R.id.arrow_left_by_streamlinehq);
        sendBtn4 = (Button) findViewById(R.id.sendBtn4);
        adminMessagesRecyclerView = (RecyclerView) findViewById(R.id.adminMessagesRecyclerView);

        adminMessagesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adminMessagesRecyclerView.setAdapter(new MessagesAdapter(getApplicationContext(), messages));

        arrow_left_by_streamlinehq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(admin_messages_activity.this, home_page_for_admin_activity.class);
                startActivity(intent);
            }
        });

        sendBtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intent = new Intent(admin_messages_activity.this, send_message_activity.class);
            startActivity(intent);
            }
        });
    }
}