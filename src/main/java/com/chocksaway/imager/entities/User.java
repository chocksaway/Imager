package com.chocksaway.imager.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@AllArgsConstructor
@SuperBuilder
@Getter
public class User {
    private String username;
    List<Picture> pictures;
}
