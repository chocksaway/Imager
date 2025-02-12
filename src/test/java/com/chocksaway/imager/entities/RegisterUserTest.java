package com.chocksaway.imager.entities;


import org.junit.Test;

import java.util.List;

public class RegisterUserTest {
    @Test
    public void testRegisterUser() {
        List<Picture> pictures = List.of(new Picture(null, "name", 100, 100, "http://example.com"));

        User registeredUser = new User("milesd");
        registeredUser.setRole("admin");
        registeredUser.setPictures(pictures);


        assert(registeredUser.getUsername().equals("milesd"));
        assert(registeredUser.getRole().equals("admin"));
    }
}
