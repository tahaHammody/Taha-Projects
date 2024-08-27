package com.example.sheltered_living;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.sheltered_living.models.Feedback;
import com.example.sheltered_living.models.Manager;
import com.example.sheltered_living.models.Message;
import com.example.sheltered_living.models.StaffMember;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

public class FirebaseDataBaseManager {

    private static final String usersCollections = "users";
    private static final String managers = "managers";

    public static void getManager(String userId,  OnCompleteListener<DocumentSnapshot> listener) {
        FirebaseFirestore.getInstance()
                .collection(managers)
                .document(userId)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()){
                            registerForUpdates(managers, userId, true);
                        }
                        listener.onComplete(task);
                    }
                });
    }

    public static void getStaffMember(String userId,  OnCompleteListener<DocumentSnapshot> listener) {
        FirebaseFirestore.getInstance()
                .collection(usersCollections)
                .document(userId)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()){
                            registerForUpdates(usersCollections, userId, false);
                        }
                        listener.onComplete(task);
                    }
                });
    }

    public static void createStaffMember(StaffMember staffMember, OnCompleteListener<Void> listener) {
        FirebaseFirestore.getInstance()
                .collection(managers)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            Manager manager = task.getResult().getDocuments().get(0).toObject(Manager.class);
                            staffMember.setManagerId(manager.getUid());
                            manager.getStaffMembers().add(staffMember);
                            FirebaseFirestore.getInstance()
                                    .collection(managers)
                                    .document(manager.getUid())
                                    .update("staffMembers", manager.getStaffMembers());
                            FirebaseFirestore.getInstance()
                                    .collection(usersCollections)
                                    .document(staffMember.getUid())
                                    .set(staffMember)
                                    .addOnCompleteListener(listener);
                        }
                    }
                });
    }

    public static void updateStaffMemberTasks(StaffMember staffMember) {
        FirebaseFirestore.getInstance()
                .collection(usersCollections)
                .document(staffMember.getUid())
                .update("tasks", staffMember.getTasks());
        FirebaseFirestore.getInstance()
                .collection(managers)
                .document(staffMember.getManagerId())
                .update("staffMembers", SessionManager.manager.getStaffMembers());
    }

    public static void updateStaffMemberShifts(StaffMember staffMember) {
        FirebaseFirestore.getInstance()
                .collection(usersCollections)
                .document(staffMember.getUid())
                .update("shifts", staffMember.getShifts());
        FirebaseFirestore.getInstance()
                .collection(managers)
                .document(staffMember.getManagerId())
                .update("staffMembers", SessionManager.manager.getStaffMembers());
    }

    public static void contactManager(Message message, OnCompleteListener onCompleteListener) {
        getManager(new OnCompleteGetManager() {
            @Override
            public void onCompleteGetManager(Manager manager) {
                manager.getStaffMembersMessages().add(message);
                FirebaseFirestore.getInstance()
                        .collection(managers)
                        .document(manager.getUid())
                        .update("staffMembersMessages", manager.getStaffMembersMessages())
                        .addOnCompleteListener(onCompleteListener);
            }
        });
    }

    public static void addFeedback(Feedback feedback) {
        getManager(new OnCompleteGetManager() {
            @Override
            public void onCompleteGetManager(Manager manager) {
                manager.getFeedbacks().add(feedback);
                FirebaseFirestore.getInstance()
                        .collection(managers)
                        .document(manager.getUid())
                        .update("feedbacks", manager.getFeedbacks());
            }
        });
    }

    private static void getManager(OnCompleteGetManager listener) {
        FirebaseFirestore.getInstance()
                .collection(managers)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            listener.onCompleteGetManager(task.getResult().getDocuments().get(0).toObject(Manager.class));
                        }
                    }
                });
    }

    private static void registerForUpdates(String collection, String uid, boolean isManager) {
        FirebaseFirestore.getInstance().collection(collection).document(uid).addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot snapshot, @Nullable FirebaseFirestoreException error) {
                if (snapshot != null && snapshot.exists()) {
                    if (isManager) {
                        SessionManager.manager = snapshot.toObject(Manager.class);
                        SqlDataBaseManager.database.saveManager(SessionManager.manager);
                    } else {
                        SessionManager.staffMember = snapshot.toObject(StaffMember.class);
                        SqlDataBaseManager.database.saveStaffMember(SessionManager.staffMember);
                    }
                }
            }
        });
    }

    public static void updateFeedback(OnCompleteListener<Void> listener) {
        FirebaseFirestore.getInstance()
                .collection(managers)
                .document(SessionManager.manager.getUid())
                .update("feedbacks", SessionManager.manager.getFeedbacks())
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        listener.onComplete(task);
                    }
                });
    }

    public static void updateMessages(OnCompleteListener<Void> listener) {
        FirebaseFirestore.getInstance()
                .collection(managers)
                .document(SessionManager.manager.getUid())
                .update("staffMembersMessages", SessionManager.manager.getStaffMembersMessages())
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        listener.onComplete(task);
                    }
                });
    }

    private interface OnCompleteGetManager{
        void onCompleteGetManager(Manager manager);
    }
}
