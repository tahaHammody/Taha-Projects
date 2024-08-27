package com.example.sheltered_living.screens.fragments;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.sheltered_living.AlertDialogUtils;
import com.example.sheltered_living.SqlDataBaseManager;
import com.example.sheltered_living.FirebaseDataBaseManager;
import com.example.sheltered_living.R;
import com.example.sheltered_living.SessionManager;
import com.example.sheltered_living.models.Message;
import com.example.sheltered_living.models.StaffMember;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class ContactManagerFragment extends Fragment {

    private ImageView closeButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_contact_manager, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        closeButton = view.findViewById(R.id.backButton2);

        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        EditText message = view.findViewById(R.id.messageEditText);
        Button send = view.findViewById(R.id.sendButton);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (message.getText() != null && !message.getText().toString().isEmpty()) {
                    sendMessage(message.getText().toString());
                } else {
                    Toast.makeText(view.getContext(), R.string.please_add_your_message, Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void sendMessage(String message) {
        AlertDialogUtils.showAlertDialogPositiveNegative(getContext(),
                R.string.please, R.string.are_you_sure, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        StaffMember staffMember = new StaffMember(SessionManager.staffMember.getUid(),
                                SessionManager.staffMember.getName(), SessionManager.staffMember.getEmail(),
                                SessionManager.staffMember.getPhone(), SessionManager.staffMember.getImageUrl());
                        Message message1 = new Message(staffMember, message, System.currentTimeMillis());
                        FirebaseDataBaseManager.contactManager(message1, new OnCompleteListener() {
                                    @Override
                                    public void onComplete(@NonNull Task task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(ContactManagerFragment.this.getContext(), R.string.sent_successfully, Toast.LENGTH_LONG).show();
                                            closeButton.performClick();
                                        } else {
                                            AlertDialogUtils.showAlertDialog(ContactManagerFragment.this.getContext(), null, task.getException().getMessage());
                                        }
                                    }
                                });
                    }
                }, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

    }
}
