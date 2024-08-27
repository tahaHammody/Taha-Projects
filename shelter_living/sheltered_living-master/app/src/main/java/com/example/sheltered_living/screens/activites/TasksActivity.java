package com.example.sheltered_living.screens.activites;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sheltered_living.AlertDialogUtils;
import com.example.sheltered_living.SqlDataBaseManager;
import com.example.sheltered_living.FirebaseDataBaseManager;
import com.example.sheltered_living.R;
import com.example.sheltered_living.SessionManager;
import com.example.sheltered_living.adapters.TasksAdapter;
import com.example.sheltered_living.callbacks.OnDeleteClickListener;
import com.example.sheltered_living.callbacks.OnUpdateClickListener;
import com.example.sheltered_living.models.StaffMember;
import com.example.sheltered_living.models.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class TasksActivity extends AppCompatActivity {

    private int staffMemberPosition;
    private TasksAdapter adapter;
    private List<Task> tasks = new ArrayList<>();
    private OnDeleteClickListener deleteTaskClickListener = new OnDeleteClickListener() {
        @Override
        public void onDeleteClicked(int position) {
            TasksActivity.this.onDeleteClicked(position);
        }
    };

    private OnUpdateClickListener updateClickListener = new OnUpdateClickListener() {
        @Override
        public void onUpdateClicked(int position) {
            openAddOrUpdateTaskScreen(position);
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_layout);
        staffMemberPosition =  getIntent().getIntExtra("staffMemberPosition", 0);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.tasks);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TasksActivity.this.finish();
            }
        });
        RecyclerView recyclerView = findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TasksAdapter(deleteTaskClickListener, updateClickListener);
        tasks.addAll(SessionManager.getSessionUserTasks(staffMemberPosition));
        adapter.setTasks(tasks);
        recyclerView.setAdapter(adapter);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddOrUpdateTaskScreen(-1);
            }
        });
        if (SessionManager.isManagerSession()) {
            fab.setVisibility(View.VISIBLE);
        }
    }

    private void openAddOrUpdateTaskScreen(int taskPosition) {
        Intent intent = new Intent(this, FragmentHolderActivity.class);
        intent.putExtra(FragmentHolderActivity.FRAGMENT_TYPE, FragmentHolderActivity.FragmentType.ADD_UPDATE_TASK);
        intent.putExtra("staffMemberPosition", staffMemberPosition);
        if (taskPosition != -1) {
            intent.putExtra("taskPosition", taskPosition);
        }
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (adapter != null) {
            tasks.clear();
            tasks.addAll(SessionManager.getSessionUserTasks(staffMemberPosition));
            adapter.notifyDataSetChanged();
        }
    }

    private void onDeleteClicked(int position) {
        AlertDialogUtils.showAlertDialogPositiveNegative(this,
                R.string.please, R.string.are_you_sure, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        StaffMember staffMember = SessionManager.manager.getStaffMembers().get(staffMemberPosition);
                        Task task = staffMember.getTasks().get(position);
                        staffMember.getTasks().remove(position);
                        tasks.remove(position);
                        SqlDataBaseManager.database.deleteTask(task.getId());
                        FirebaseDataBaseManager.updateStaffMemberTasks(staffMember);
                        Toast.makeText(TasksActivity.this, R.string.deleted_successfully, Toast.LENGTH_LONG).show();
                        adapter.notifyItemRemoved(position);
                    }
                }, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
    }

}
