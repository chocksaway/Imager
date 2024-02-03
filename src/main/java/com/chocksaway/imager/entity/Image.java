package com.chocksaway.imager.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "images")
public class Image {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String name;
    //private Description description;

    //private Display display;

    protected Image() {}

    public Image(String name, Description description, Display display) {
        this.name = name;
        //this.display = display;
        //this.description = description;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

//    public Description getDescription() {
//        return description;
//    }
//
//    public Display getDisplay() {
//        return display;
//    }
}
