package com.chocksaway.imager.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
public class User {
    @JsonProperty("username")
    private String username;

    @JsonProperty("age")
    private int age;

    @JsonProperty("pictures")
    List<Picture> pictures;
}
