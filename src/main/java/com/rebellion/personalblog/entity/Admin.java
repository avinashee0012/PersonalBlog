package com.rebellion.personalblog.entity;

public class Admin {
    private String username;
    private String password;

    public Admin() {
        this.username = "user";
        this.password = "password";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
