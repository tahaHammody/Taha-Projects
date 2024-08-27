package com.example.sheltered_living.screens.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;

import androidx.fragment.app.FragmentManager;

import com.example.sheltered_living.R;
import com.example.sheltered_living.SessionManager;


public class HomeMangerActivity extends AppCompatActivity {
    private FragmentManager fragmentManager;
    private ImageView menuIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_manger);
        menuIcon = findViewById(R.id.right);
        fragmentManager = getSupportFragmentManager();
        Button taskBT = findViewById(R.id.myStaffBT);
        taskBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeMangerActivity.this, StaffMemberActivity.class);
                startActivity(i);
            }
        });

        findViewById(R.id.messageBT).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeMangerActivity.this, MessagesActivity.class);
                startActivity(i);
            }
        });
    }

    public void showPopupMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.inflate(R.menu.menu_options);

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                // Handle menu item click events here
                switch (item.getItemId()) {
                    case R.id.logout:
                        SessionManager.logOut();
                        Intent logoutIntent = new Intent(HomeMangerActivity.this, MainActivity.class);
                        startActivity(logoutIntent);
                        HomeMangerActivity.this.finish();
                        return true;
                    case  R.id.feedbacks:
                        Intent intent = new Intent(HomeMangerActivity.this, FeedbacksActivity.class);
                        startActivity(intent);
                        return true;
                    default:
                        return false;
                }
            }
        });

        popupMenu.show();
    }
}
