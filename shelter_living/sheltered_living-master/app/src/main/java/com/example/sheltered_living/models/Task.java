package com.example.sheltered_living.models;

public class Task {

    private String date;
    private String title;
    private String description;
    private Long id;


    public Task(String description, String title, String date, Long id) {
        this.date = date;
        this.description = description;
        this.title = title;
        this.id = id;
    }

    public Task(){}

    public String getDate() {
        return date;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
