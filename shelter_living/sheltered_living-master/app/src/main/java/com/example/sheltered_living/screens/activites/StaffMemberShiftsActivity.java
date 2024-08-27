package com.example.sheltered_living.screens.activites;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.widget.Toolbar;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sheltered_living.SqlDataBaseManager;
import com.example.sheltered_living.FirebaseDataBaseManager;
import com.example.sheltered_living.R;
import com.example.sheltered_living.SessionManager;
import com.example.sheltered_living.adapters.ShiftsAdapter;
import com.example.sheltered_living.callbacks.OnDeleteClickListener;
import com.example.sheltered_living.models.StaffMember;
import com.example.sheltered_living.screens.fragments.CalenderFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StaffMemberShiftsActivity extends AppCompatActivity {

    private ShiftsAdapter shiftsAdapter;
    private List<Long> shifts;
    int position;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_layout);
        position = getIntent().getIntExtra("position", -1);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.shifts);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StaffMemberShiftsActivity.this.finish();
            }
        });
        RecyclerView recyclerView = findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        shifts = buildListItems();
        OnDeleteClickListener deleteClickListener = new OnDeleteClickListener() {
            @Override
            public void onDeleteClicked(int position) {
                StaffMember staffMember = getStaffMember();
                staffMember.getShifts().remove(String.valueOf(shifts.get(position)));
                long shift = shifts.remove(position);
                FirebaseDataBaseManager.updateStaffMemberShifts(staffMember);
                SqlDataBaseManager.database.deleteShit(staffMember, String.valueOf(shift));
                shiftsAdapter.notifyItemRemoved(position);
            }
        };



        shiftsAdapter = new ShiftsAdapter(shifts, deleteClickListener);
        recyclerView.setAdapter(shiftsAdapter);
        if (SessionManager.isManagerSession()) {
            FloatingActionButton floatingActionButton = findViewById(R.id.fab);
            floatingActionButton.setVisibility(View.VISIBLE);
            floatingActionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openCalenderDialog(position);
                }
            });
        }

    }

    private void openCalenderDialog(int position) {
        CalenderFragment.openDialog(getSupportFragmentManager(), position, new Runnable() {
            @Override
            public void run() {
                if (shiftsAdapter != null) {
                    shifts = buildListItems();
                    shiftsAdapter.setItems(shifts);
                }
            }
        });
    }

    private StaffMember getStaffMember() {
        return SessionManager.isManagerSession()
                ? SessionManager.manager.getStaffMembers().get(position) : SessionManager.staffMember;
    }

    private List<Long> buildListItems() {
        if (getStaffMember().getShifts() != null ) {
            return getStaffMember().getShifts().values().stream().sorted().collect(Collectors.toList());
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
