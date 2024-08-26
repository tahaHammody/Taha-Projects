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
import Logic.User;

public class teacher_messages_activity extends AppCompatActivity {

    private ImageView arrow_left_by_streamlinehq;
    private Button sendButton3;
    private RecyclerView teacherMessagesRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacher_messages);

        List<Message> messages = new ArrayList<Message>();
        messages.add(new Message(new User("Hala", R.drawable.female_woman_avatar_people_person_white_tone_icon_159370), "Biology Class Homework", "can you explain the first question"));
        messages.add(new Message(new User("kareem", R.drawable.male_boy_person_people_avatar_icon_159358), "English Exam", "can you please delay the exam to thursday"));
        messages.add(new Message(new User("Loai", R.drawable.male_people_avatar_man_boy_curly_hair_icon_159362), "Math class", "what do we have to bring to the class?"));
        messages.add(new Message(new User("Sara", R.drawable.female_woman_person_people_avatar_icon_159366), "Homework delay", "what does the second question mean?"));

        arrow_left_by_streamlinehq = (ImageView) findViewById(R.id.arrow_left_by_streamlinehq);
        sendButton3 = (Button) findViewById(R.id.sendButton3);
        teacherMessagesRecyclerView = (RecyclerView) findViewById(R.id.teacherMessagesRecyclerView);

        teacherMessagesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        teacherMessagesRecyclerView.setAdapter(new MessagesAdapter(getApplicationContext(), messages));


        arrow_left_by_streamlinehq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(teacher_messages_activity.this, home_page_for_teacher_activity.class);
                startActivity(intent);
            }
        });

        sendButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(teacher_messages_activity.this, send_message_activity.class);
                startActivity(intent);
            }
        });
    }
}