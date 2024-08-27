package com.example.sheltered_living.screens.activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sheltered_living.SqlDataBaseManager;
import com.example.sheltered_living.FirebaseDataBaseManager;
import com.example.sheltered_living.R;
import com.example.sheltered_living.models.Feedback;

public class ElderlyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elderly);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.staff_members);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ElderlyActivity.this.finish();
            }
        });


        EditText name = findViewById(R.id.nameEditText);
        EditText room = findViewById(R.id.roomNumber);
        EditText description = findViewById(R.id.descriptionET);


        Button sendButton = findViewById(R.id.sendButton);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValidFeedback(name.getText(), room.getText(), description.getText())) {
                    Feedback feedback = new Feedback(description.getText().toString(), name.getText().toString(), Integer.parseInt(room.getText().toString()), System.currentTimeMillis());
                    FirebaseDataBaseManager.addFeedback(feedback);
                    ElderlyActivity.this.finish();
                } else {
                    Toast.makeText(ElderlyActivity.this, getString(R.string.some_details_are_missing), Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private boolean isValidFeedback(Editable name, Editable room, Editable description) {
        return name!= null && !name.toString().isEmpty()
        &&  room!= null && !room.toString().isEmpty()
       && description!= null && !description.toString().isEmpty();
    }
}
