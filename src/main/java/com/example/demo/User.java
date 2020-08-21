package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {

    @Id
    @GeneratedValue
    private Long userID;

    private String name;

    public User() {}

    public User(String name) {
        this.name = name;
    }

    public String get() {
        return name;
    }
}
