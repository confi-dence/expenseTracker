package com.confidence.quiz.models;

import java.time.LocalDateTime;

public class User {
    private int id;
    private String name;
    private String email;
    private String password;
    private LocalDateTime createdat;

    public User(int id, String name, String email, String password, LocalDateTime createdat) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.createdat = createdat;
    }

    public LocalDateTime getCreatedat() {
        return createdat;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }
}
