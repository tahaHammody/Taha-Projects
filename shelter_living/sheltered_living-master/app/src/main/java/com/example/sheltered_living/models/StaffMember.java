package com.example.sheltered_living.models;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class StaffMember extends User implements Serializable {

    private ArrayList<Task> tasks;
    private Map<String, Long> shifts;
    private String  imageUrl;
    private String phone;

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void setShifts(Map<String, Long> shifts) {
        this.shifts = shifts;
    }

    private String email;

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    private String managerId;

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public StaffMember(String uid, String name, String email, String phone, String imageUrl) {
        super(uid, name);
        this.email = email;
        this.phone = phone;
        this.imageUrl = imageUrl;
        tasks = new ArrayList<>();
        shifts = new HashMap<>();
    }

    public Map<String, Long> getShifts() {
        return shifts;
    }

    public String getManagerId() {
        return managerId;
    }

    public StaffMember(){
        super();
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }
}
