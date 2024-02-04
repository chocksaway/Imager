package com.chocksaway.imager.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "description")
public class Description {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String name;

    public Description(String name) {
        this.name = name;
    }

    protected Description() {}

    @Override
    public String toString() {
        return "[ " + this.name + " ]";
    }
}
