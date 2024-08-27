package com.example.sheltered_living;

import com.example.sheltered_living.models.Manager;
import com.example.sheltered_living.models.StaffMember;
import com.example.sheltered_living.models.Task;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class SessionManager {
    public static String uid;
    public static Manager manager;
    public static StaffMember staffMember;

    public static boolean isManagerSession() {
        return manager != null;
    }

    public static ArrayList<Task> getSessionUserTasks(int position) {
        return manager != null ? manager.getStaffMembers().get(position).getTasks() : staffMember.getTasks();
    }

    public static void logOut() {
        FirebaseAuth.getInstance().signOut();
        SqlDataBaseManager.database.clear();
        manager = null;
        staffMember = null;
        uid = null;
    }

}
