package com.example.sheltered_living.screens.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

import com.example.sheltered_living.AlertDialogUtils;
import com.example.sheltered_living.FirebaseDataBaseManager;
import com.example.sheltered_living.SqlDataBaseManager;
import com.example.sheltered_living.screens.activites.HomeMangerActivity;
import com.example.sheltered_living.R;
import com.example.sheltered_living.SessionManager;
import com.example.sheltered_living.models.Manager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.DocumentSnapshot;

public class ManagerLoginFragment extends Fragment {

    private TextInputLayout password;
    private TextInputLayout email;
    private Button loginBT;
    private AppCompatTextView error;
    FirebaseAuth firebaseAuth;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebaseAuth = FirebaseAuth.getInstance();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.manger_login_fragment, container, false);
        password = view.findViewById(R.id.password);
        email = view.findViewById(R.id.email);
        loginBT = view.findViewById(R.id.loginButton);
        error = view.findViewById(R.id.emailError);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loginBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (email.getEditText().getText() == null || "".equals(email.getEditText().getText().toString())) {
                    error.setVisibility(View.VISIBLE);
                } else {
                    tryToLoginIn();
                }
            }
        });
    }

    private void tryToLoginIn() {
        firebaseAuth.signInWithEmailAndPassword(email.getEditText().getText().toString(), password.getEditText().getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser user = firebaseAuth.getCurrentUser();
                    user.updateProfile(new UserProfileChangeRequest
                            .Builder()
                            .setDisplayName("manager")
                            .build());
                    FirebaseDataBaseManager.getManager(user.getUid(), new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                                SessionManager.manager = task.getResult().toObject(Manager.class);
                                SqlDataBaseManager.database.saveManager(SessionManager.manager);
                                Intent intent = new Intent(ManagerLoginFragment.this.getActivity(), HomeMangerActivity.class);
                                startActivity(intent);
                                ManagerLoginFragment.this.getActivity().finish();
                            } else {
                                AlertDialogUtils.showAlertDialog(ManagerLoginFragment.this.getActivity(), "Error", task.getException().getMessage());
                            }
                        }
                    });
                } else {
                    AlertDialogUtils.showAlertDialog(ManagerLoginFragment.this.getActivity(), "Error", task.getException().getMessage());
                }
            }
        });
    }
}
