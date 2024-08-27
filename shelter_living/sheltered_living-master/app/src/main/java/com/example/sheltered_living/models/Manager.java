package com.example.sheltered_living.models;

import java.io.Serializable;
import java.util.ArrayList;

public class Manager extends User implements Serializable {

    private ArrayList<StaffMember> staffMembers;
    private ArrayList<Feedback> feedbacks;
    private ArrayList<Message> staffMembersMessages;


    public Manager(String uid, String name) {
        super(uid, name);
        this.staffMembers = new ArrayList<>();
        this.feedbacks = new ArrayList<>();
        this.staffMembersMessages = new ArrayList<>();
    }

    public Manager(){}

    public void setStaffMembers(ArrayList<StaffMember> staffMembers) {
        this.staffMembers = staffMembers;
    }

    public void setFeedbacks(ArrayList<Feedback> feedbacks) {
        this.feedbacks = feedbacks;
    }

    public void setStaffMembersMessages(ArrayList<Message> staffMembersMessages) {
        this.staffMembersMessages = staffMembersMessages;
    }

    public ArrayList<Message> getStaffMembersMessages() {
        return staffMembersMessages == null ? new ArrayList<>() : staffMembersMessages;
    }

    public ArrayList<Feedback> getFeedbacks() {
        return feedbacks == null ? new ArrayList() : feedbacks;
    }

    public ArrayList<StaffMember> getStaffMembers() {
        return staffMembers;
    }
}
