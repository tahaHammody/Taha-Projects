package com.example.sheltered_living.models;

import java.io.Serializable;

public class Feedback implements Serializable {
    private String message;
    private String name;
    private int roomNumber;
    private long id;


    public Feedback(String message, String name, int roomNumber, long id) {
        this.message = message;
        this.name = name;
        this.roomNumber = roomNumber;
        this.id = id;
    }

    public Feedback(){}

    public long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public String getName() {
        return name;
    }

    public int getRoomNumber() {
        return roomNumber;
    }
}
