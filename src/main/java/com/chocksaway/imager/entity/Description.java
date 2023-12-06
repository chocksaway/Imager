package com.chocksaway.imager.entity;

import java.io.Serializable;

public record Description(String description) implements Serializable {
    @Override
    public String toString() {
        return "[ " + this.description + " ]";
    }
}
