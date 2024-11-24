package com.chocksaway.imager.entities;

import org.junit.jupiter.api.Test;

import java.util.List;

public class RegisterUserTest {
    @Test
    public void testRegisterUser() {
        List<Picture> pictures = List.of(Picture.builder()
                .height(100)
                .width(100)
                .url("http://example.com")
                .build());

        RegisteredUser registeredUser = RegisteredUser.builder()
                .username("milesd")
                .role("admin")
                //.pictures(pictures)
                .build();

        assert(registeredUser.getUsername().equals("milesd"));
        assert(registeredUser.getRole().equals("admin"));
    }
}
