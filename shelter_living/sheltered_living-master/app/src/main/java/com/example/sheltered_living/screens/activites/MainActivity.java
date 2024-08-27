package com.example.sheltered_living.screens.activites;

import static com.example.sheltered_living.screens.activites.FragmentHolderActivity.FragmentType.ABOUT_ME;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.sheltered_living.AlertDialogUtils;
import com.example.sheltered_living.SqlDataBaseManager;
import com.example.sheltered_living.FirebaseDataBaseManager;
import com.example.sheltered_living.R;
import com.example.sheltered_living.SessionManager;
import com.example.sheltered_living.models.Manager;
import com.example.sheltered_living.models.StaffMember;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;

public class MainActivity extends AppCompatActivity {
    Button aboutUsButton, elderlyButton, staffButton, managerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SqlDataBaseManager.init(this.getApplicationContext());
        setContentView(R.layout.activity_main);
        checkIfAlreadySignedIn();
        aboutUsButton = findViewById(R.id.aboutButton);
        elderlyButton = findViewById(R.id.elderlyButton);
        staffButton = findViewById(R.id.staffButton);
        managerButton = findViewById(R.id.managerButton);

        elderlyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ElderlyActivity.class);
                startActivity(intent);
            }
        });

        aboutUsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayAboutUsFragment();
                disableAboutUsButton();
            }
        });

        staffButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FragmentHolderActivity.class);
                intent.putExtra(FragmentHolderActivity.FRAGMENT_TYPE, FragmentHolderActivity.FragmentType.STUFF_MEMBER_LOGIN);
                startActivity(intent);
            }
        });

        managerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FragmentHolderActivity.class);
                intent.putExtra(FragmentHolderActivity.FRAGMENT_TYPE, FragmentHolderActivity.FragmentType.MANAGER_LOGIN);
                startActivity(intent);
            }
        });
    }

    private void checkIfAlreadySignedIn() {
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            if ("manager".equals(FirebaseAuth.getInstance().getCurrentUser().getDisplayName())){
                SessionManager.manager = SqlDataBaseManager.database.getManager();
                FirebaseDataBaseManager.getManager(FirebaseAuth.getInstance().getCurrentUser().getUid(), new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()){
                            SessionManager.manager = task.getResult().toObject(Manager.class);
                            SqlDataBaseManager.database.saveManager(SessionManager.manager);
                            Intent intent = new Intent(MainActivity.this, HomeMangerActivity.class);
                            startActivity(intent);
                            MainActivity.this.finish();
                        }
                    }
                });
            }else {
                SessionManager.staffMember = SqlDataBaseManager.database.getStaffMember();
                FirebaseDataBaseManager.getStaffMember(FirebaseAuth.getInstance().getCurrentUser().getUid(), new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            SessionManager.staffMember = task.getResult().toObject(StaffMember.class);
                            SqlDataBaseManager.database.saveStaffMember(SessionManager.staffMember);
                            Intent intent = new Intent(MainActivity.this, HomeStaffActivity.class);
                            startActivity(intent);
                            MainActivity.this.finish();
                        }
                    }
                });
            }
        }
    }

    private void displayAboutUsFragment() {
       Intent intent = new Intent(this, FragmentHolderActivity.class);
       intent.putExtra(FragmentHolderActivity.FRAGMENT_TYPE, ABOUT_ME);
       startActivity(intent);
    }

    public void disableAboutUsButton() {
        aboutUsButton.setEnabled(false);
    }
}
