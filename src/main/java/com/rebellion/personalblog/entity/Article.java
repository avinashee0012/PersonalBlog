package com.rebellion.personalblog.entity;

import java.time.LocalDate;

public class Article {

    private int id;
    private String title;
    private LocalDate date;
    private String content;

    public Article(int id, String title, LocalDate date, String content) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.content = content;
    }

    public Article() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Article [id=" + id + ", title=" + title + ", date=" + date + ", content=" + content + "]";
    }
}
