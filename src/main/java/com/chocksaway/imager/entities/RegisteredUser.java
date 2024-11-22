package com.chocksaway.imager.entities;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class RegisteredUser extends User {
    private String role;
}
