package com.chocksaway.imager.entities;

import lombok.experimental.SuperBuilder;

@SuperBuilder
public class Picture {
    private int height;
    private int width;
    private String url;
}
