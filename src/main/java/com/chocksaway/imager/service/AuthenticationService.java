package com.chocksaway.imager.service;

import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    public boolean authenticate(String name, String password) {
        return name.equals("milesd") && password.equals("milesd");
    }
}
