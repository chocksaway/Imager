package com.chocksaway.imager.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Image {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String name;
    private Description description;

    private Display display;

    protected Image() {}

    public Image(String name, Description description, Display display) {
        this.display = display;
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public Description getDescription() {
        return description;
    }
}
