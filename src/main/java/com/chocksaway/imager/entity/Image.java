package com.chocksaway.imager.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "images")
public class Image {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id", referencedColumnName = "description_id")
    private Description description;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id", referencedColumnName = "display_id")
    private Display display;

    protected Image() {}

    public Image(String name, Description description, Display display) {
        this.name = name;
        this.display = display;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public Description getDescription() {
        return description;
    }

    public Display getDisplay() {
        return display;
    }
}
