package com.rebellion.personalblog.entity;

public class Index {
    private int id;
    // TODO: date should be in LocalDate datatype
    private String date;
    private String title;

    public Index(int id, String date, String title) {
        this.id = id;
        this.date = date;
        this.title = title;
    }

    public Index() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
