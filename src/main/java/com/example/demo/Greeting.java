package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Greeting {

    @Id
    @GeneratedValue
    private Long greetingID;

    private String title;

    @OneToMany
    private Set<User> users;

    public Greeting(String title) {
        this.title = title;
    }


    public void addUser(User user) {
        if (users == null) users = new HashSet<>();
        users.add(user);
    }

    public String getTitle() {
        return this.title;
    }

    public Set<User> getUsers() {
        return users;
    }

    @Override
    public String toString() {
        return "id=" + greetingID + "; title=" + title + "; users=" + users;
    }
}
