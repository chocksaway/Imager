package com.chocksaway.imager.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class User {
    @JsonProperty("username")
    private String username;

    @JsonProperty("age")
    private int age;

    @JsonProperty("role")
    private String role;

    @JsonProperty("pictures")
    List<Picture> pictures;

    public User(String username) {
        this.username = username;
    }

    public Object getUsername() {
        return this.username;
    }

    public void setPictures(List<Picture> pictures) {
        this.pictures = pictures;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return this.role;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
