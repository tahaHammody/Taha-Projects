package com.example.sheltered_living.screens.fragments;

import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.sheltered_living.AlertDialogUtils;
import com.example.sheltered_living.SqlDataBaseManager;
import com.example.sheltered_living.FirebaseDataBaseManager;
import com.example.sheltered_living.R;
import com.example.sheltered_living.SessionManager;
import com.example.sheltered_living.models.StaffMember;
import com.example.sheltered_living.models.Task;

public class AddOrUpdateFragment extends Fragment {

    private EditText titleET;
    private EditText descriptionET;
    private EditText dateET;
    private Button save;
    private StaffMember staffMember;
    private Task task;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        staffMember = (StaffMember) SessionManager.manager.getStaffMembers().get(getArguments().getInt("staffMemberPosition"));
        int taskPosition = getArguments().getInt("taskPosition");
        if (taskPosition != -1) {
            task = staffMember.getTasks().get(taskPosition);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.add_update_task_layout, container, false);
        titleET = view.findViewById(R.id.titleET);
        descriptionET = view.findViewById(R.id.descriptionET);
        dateET = view.findViewById(R.id.dateET);
        save = view.findViewById(R.id.save);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (task != null) {//its mean this is update not add
            titleET.setText(task.getTitle());
            descriptionET.setText(task.getDescription());
            dateET.setText(task.getDate());
        }
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (titleET.getText() != null && !titleET.getText().toString().equals("")
                && descriptionET.getText() != null && !descriptionET.getText().toString().equals("")
                && dateET.getText() != null && !dateET.getText().toString().equals("")) {
                    AlertDialogUtils.showAlertDialogPositiveNegative(v.getContext(),
                            R.string.please, R.string.are_you_sure, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    onApprove();
                                }
                            }, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                }
            }
        });
    }

    private void onApprove() {
        Task temp = task;
        if (task != null){// its mean is update
            task.setTitle(titleET.getText().toString());
            task.setDescription(descriptionET.getText().toString());
            task.setDate(dateET.getText().toString());
            SqlDataBaseManager.database.updateTask(temp);
        } else {
            temp = new Task(descriptionET.getText().toString(), titleET.getText().toString(), dateET.getText().toString(), System.currentTimeMillis());
            staffMember.getTasks().add(temp);
            SqlDataBaseManager.database.insertTask(temp, staffMember.getUid(), null);
        }

        FirebaseDataBaseManager.updateStaffMemberTasks(staffMember);
        Toast.makeText(this.getActivity().getApplicationContext(), R.string.saved_successfully, Toast.LENGTH_LONG).show();
        AddOrUpdateFragment.this.getActivity().finish();
    }
}
