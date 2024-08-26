package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import Logic.Message;
import Logic.User;

public class student_messages_activity extends AppCompatActivity {

    private ImageView arrow_left_by_streamlinehq;
    private Button sendBtn3;
    private RecyclerView studentsMessagesRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_messages);

        List<Message> messages = new ArrayList<Message>();
        messages.add(new Message(new User("Hala", R.drawable.female_woman_avatar_people_person_white_tone_icon_159370), "Biology Class Homework", "you have to solve homework before saturday"));
        messages.add(new Message(new User("kareem", R.drawable.male_boy_person_people_avatar_icon_159358), "English Exam", "Hello, did you study well for english exam"));
        messages.add(new Message(new User("Loai", R.drawable.male_people_avatar_man_boy_curly_hair_icon_159362), "Math class", "do you want to come to math class tomorrow"));
        messages.add(new Message(new User("Sara", R.drawable.female_woman_person_people_avatar_icon_159366), "Homework delay", "hebrew homework delayed to thursday"));

        arrow_left_by_streamlinehq = (ImageView) findViewById(R.id.arrow_left_by_streamlinehq);
        sendBtn3 = (Button) findViewById(R.id.sendBtn3);
        studentsMessagesRecyclerView = (RecyclerView) findViewById(R.id.studentsMessagesRecyclerView);

        studentsMessagesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        studentsMessagesRecyclerView.setAdapter(new MessagesAdapter(getApplicationContext(), messages));


        arrow_left_by_streamlinehq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(student_messages_activity.this, home_page_for_student_activity.class);
                startActivity(intent);
            }
        });

        sendBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(student_messages_activity.this, send_message_activity.class);
                startActivity(intent);
            }
        });
    }


}