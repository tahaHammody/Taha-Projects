package com.example.sheltered_living.models;

import java.io.Serializable;

public class User implements Serializable {
    protected String name;
    protected String uid;


    public User(String uid,String name) {
        this.uid = uid;
        this.name = name;
    }

    public User(){}

    public String getUid() {
        return uid;
    }

    public String getName(){return name;}

    public void setUid(String uid) {
        this.uid = uid;
    }
}
