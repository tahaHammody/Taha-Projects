package com.example.sheltered_living.screens.activites;

import static com.example.sheltered_living.screens.activites.FragmentHolderActivity.FragmentType.CONTACT;
import static com.example.sheltered_living.screens.activites.FragmentHolderActivity.FragmentType.PROFILE;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;

import com.example.sheltered_living.R;
import com.example.sheltered_living.SessionManager;


public class HomeStaffActivity extends AppCompatActivity {
    Button mytasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_staff);

        mytasks = findViewById(R.id.btnMyTasks);
        mytasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tasksIntent = new Intent(HomeStaffActivity.this, TasksActivity.class);
                startActivity(tasksIntent);
            }
        });

        findViewById(R.id.btnShiftScheduling).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tasksIntent = new Intent(HomeStaffActivity.this, StaffMemberShiftsActivity.class);
                startActivity(tasksIntent);
            }
        });



    }


    public void showPopupMenu1(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.inflate(R.menu.menu1_options); // Replace with your menu file

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                // Handle menu item click events here
                switch (item.getItemId()) {
                    case R.id.profile1:
                        displayProfileFragment(); // Call the method to display the profile fragment
                        return true;
                    case R.id.contactmanger:
                        displayContactManagerFragment();
                        return true;
                    case R.id.logout1:
                        SessionManager.logOut();
                        Intent logoutIntent = new Intent(HomeStaffActivity.this, MainActivity.class);
                        startActivity(logoutIntent);
                        HomeStaffActivity.this.finish();
                        return true;
                    default:
                        return false;
                }
            }
        });

        popupMenu.show();
    }

    private void displayProfileFragment() {
        Intent intent = new Intent(this, FragmentHolderActivity.class);
        intent.putExtra(FragmentHolderActivity.FRAGMENT_TYPE, PROFILE);
        startActivity(intent);
    }

    private void displayContactManagerFragment() {
        Intent intent = new Intent(this, FragmentHolderActivity.class);
        intent.putExtra(FragmentHolderActivity.FRAGMENT_TYPE, CONTACT);
        startActivity(intent);
    }

}
