package com.chocksaway.imager.entities;

import org.junit.jupiter.api.Test;

import java.util.List;

public class RegisterUserTest {
    @Test
    public void testRegisterUser() {
        List<Picture> pictures = List.of(new Picture(null, "name", 100, 100, "http://example.com"));

        RegisteredUser registeredUser = RegisteredUser.builder()
                .username("milesd")
                .role("admin")
                .pictures(pictures)
                .build();

        assert(registeredUser.getUsername().equals("milesd"));
        assert(registeredUser.getRole().equals("admin"));
    }
}
