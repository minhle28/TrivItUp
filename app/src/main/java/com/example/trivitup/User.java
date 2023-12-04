package com.example.trivitup;

public class User {
    public User(String name, String email, String pass) {
        this.name = name;
        this.email = email;
        this.pass = pass;

    }

    public User() {
    }

    private String name;
    private String email;
    private String pass;

    public long getPoints() {
        return points;
    }

    public void setPoints(long points) {
        this.points = points;
    }

    private long points;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }



}
