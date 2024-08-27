package com.example.sheltered_living.models;

import java.io.Serializable;

public class Message implements Serializable {

    String message;
    StaffMember staffMember;
    long id;

    public Message(StaffMember staffMember, String message, long id) {
        this.message = message;
        this.staffMember = staffMember;
        this.id = id;
    }

    public Message(){}

    public long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public StaffMember getStaffMember() {
        return staffMember;
    }
}
