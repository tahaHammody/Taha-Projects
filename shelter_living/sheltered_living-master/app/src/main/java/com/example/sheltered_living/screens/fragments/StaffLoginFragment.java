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
import com.example.sheltered_living.screens.activites.HomeStaffActivity;
import com.example.sheltered_living.R;
import com.example.sheltered_living.SessionManager;
import com.example.sheltered_living.models.StaffMember;
import com.example.sheltered_living.screens.activites.CreateProfileActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.DocumentSnapshot;

public class StaffLoginFragment extends Fragment {

    private TextInputLayout password;
    private TextInputLayout email;
    private Button loginBT;
    private Button signUpBT;
    private AppCompatTextView error;
    FirebaseAuth firebaseAuth;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
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

        signUpBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValidInput()) {
                    signUp();
                } else {
                    error.setVisibility(View.VISIBLE);
                }

            }
        });
    }

    private boolean isValidInput() {
        return email.getEditText().getText() != null && !"".equals(email.getEditText().getText().toString());
    }

    private void signUp() {
        firebaseAuth.createUserWithEmailAndPassword(email.getEditText().getText().toString(), password.getEditText().getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    if (task.isSuccessful()) {
                        FirebaseUser user = firebaseAuth.getCurrentUser();
                        SessionManager.uid = user.getUid();
                        goToCreateProfile();
                    } else {
                        AlertDialogUtils.showAlertDialog(StaffLoginFragment.this.getActivity(), "Error", task.getException().getMessage());
                    }
                }
            }
        });
    }

    private void goToCreateProfile() {
        Intent intent = new Intent(StaffLoginFragment.this.getActivity(), CreateProfileActivity.class);
        startActivity(intent);
        StaffLoginFragment.this.getActivity().finish();
    }

    private void tryToLoginIn() {
        firebaseAuth.signInWithEmailAndPassword(email.getEditText().getText().toString(), password.getEditText().getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser user = firebaseAuth.getCurrentUser();
                    user.updateProfile(new UserProfileChangeRequest
                            .Builder()
                            .setDisplayName("staffMember")
                            .build());
                    FirebaseDataBaseManager.getStaffMember(user.getUid(), new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                                SessionManager.staffMember = task.getResult().toObject(StaffMember.class);
                                SqlDataBaseManager.database.saveStaffMember(SessionManager.staffMember);
                                Intent intent = new Intent(StaffLoginFragment.this.getActivity(), HomeStaffActivity.class);
                                startActivity(intent);
                                StaffLoginFragment.this.getActivity().finish();
                            } else {
                                AlertDialogUtils.showAlertDialog(StaffLoginFragment.this.getActivity(), "Error", task.getException().getMessage());
                            }
                        }
                    });
                } else {
                    AlertDialogUtils.showAlertDialog(StaffLoginFragment.this.getActivity(), "Error", task.getException().getMessage());
                }
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.staff_login_fragment, container, false);
        password = view.findViewById(R.id.password);
        email = view.findViewById(R.id.email);
        loginBT = view.findViewById(R.id.loginButton);
        signUpBT = view.findViewById(R.id.signUpButton);
        error = view.findViewById(R.id.emailError);
        return view;
    }
}
