package com.example.sheltered_living.screens.activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.sheltered_living.R;
import com.example.sheltered_living.SessionManager;
import com.example.sheltered_living.adapters.StaffMembersAdapter;
import com.example.sheltered_living.callbacks.OnAddShiftClickListener;
import com.example.sheltered_living.callbacks.OnAddTaskClickListener;
import com.example.sheltered_living.models.StaffMember;

public class StaffMemberActivity extends AppCompatActivity {
    private StaffMembersAdapter adapter;

    private final OnAddShiftClickListener onAddShiftClickListener = new OnAddShiftClickListener() {
        @Override
        public void onAddShiftClicked(int position, StaffMember staffMember) {
            openScheduleShiftScreen(position);
        }
    };

    private final OnAddTaskClickListener onAddTaskClickListener = new OnAddTaskClickListener() {
        @Override
        public void onAddTaskClicked(int position, StaffMember staffMember) {
            openAddTaskScreen(position);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_layout);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.staff_members);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StaffMemberActivity.this.finish();
            }
        });
        RecyclerView recyclerView = findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new StaffMembersAdapter(onAddShiftClickListener, onAddTaskClickListener);
        adapter.setItems(SessionManager.manager.getStaffMembers());
        recyclerView.setAdapter(adapter);
    }

    private void openScheduleShiftScreen(int position) {
        Intent intent = new Intent(this, StaffMemberShiftsActivity.class);
        intent.putExtra("position", position);
        startActivity(intent);
    }

    private void openAddTaskScreen(int position) {
        Intent intent = new Intent(this, TasksActivity.class);
        intent.putExtra("staffMemberPosition", position);
        startActivity(intent);

    }

}
