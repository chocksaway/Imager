package com.chocksaway.imager.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Login {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String password;

    public Login(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public Login() {
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
}
