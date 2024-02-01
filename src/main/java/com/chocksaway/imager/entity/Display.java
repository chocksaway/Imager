package com.chocksaway.imager.entity;

import java.io.Serializable;

public record Display(int height, int width, boolean border) implements Serializable {

}
