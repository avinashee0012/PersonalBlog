package com.rebellion.personalblog.entity;

public class Article {

    private int id;
    private String title;
    private String publishdate;
    private String content;

    public Article(int id, String title, String publishdate, String content) {
        this.id = id;
        this.title = title;
        this.publishdate = publishdate;
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

    public String getPublishdate() {
        return publishdate;
    }

    public void setPublishdate(String publishdate) {
        this.publishdate = publishdate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Article [id=" + id + ", title=" + title + ", publishdate=" + publishdate + ", content=" + content + "]";
    }

}
