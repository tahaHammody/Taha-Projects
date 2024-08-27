package com.example.sheltered_living.screens.activites;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sheltered_living.AlertDialogUtils;
import com.example.sheltered_living.FirebaseDataBaseManager;
import com.example.sheltered_living.R;
import com.example.sheltered_living.SessionManager;
import com.example.sheltered_living.SqlDataBaseManager;
import com.example.sheltered_living.adapters.MessagesAdapter;
import com.example.sheltered_living.callbacks.OnDeleteClickListener;
import com.example.sheltered_living.models.Message;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class MessagesActivity extends AppCompatActivity {
    private MessagesAdapter adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_layout);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.messages);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MessagesActivity.this.finish();
            }
        });
        RecyclerView recyclerView = findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MessagesAdapter(SessionManager.manager.getStaffMembersMessages(), new OnDeleteClickListener() {
             @Override
             public void onDeleteClicked(int position) {
                 Message message = SessionManager.manager.getStaffMembersMessages().remove(position);
                 SqlDataBaseManager.database.deleteMessage(message);
                 adapter.notifyItemRemoved(position);
                 FirebaseDataBaseManager.updateMessages(new OnCompleteListener<Void>() {
                     @Override
                     public void onComplete(@NonNull Task<Void> task) {
                         if (task.isSuccessful()) {
                             Toast.makeText(MessagesActivity.this, R.string.deleted_successfully, Toast.LENGTH_LONG).show();
                         } else {
                             AlertDialogUtils.showAlertDialog(MessagesActivity.this, null, task.getException().getMessage());
                         }
                     }
                 });
             }
         });
        recyclerView.setAdapter(adapter);
    }
}
