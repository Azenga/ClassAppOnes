package com.shadow.classappones.models;

import java.io.Serializable;

public class User implements Serializable {

    private String id;

    private String name, location, group;
    private int age;

    public User() {
    }

    public User(String name, String location, String group, int age) {
        this.name = name;
        this.location = location;
        this.group = group;
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getType() {
        return group;
    }

    public int getAge() {
        return age;
    }

    public void setId(String id) {
        this.id = id;
    }
}
