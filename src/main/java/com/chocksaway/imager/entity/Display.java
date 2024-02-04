package com.chocksaway.imager.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "display")
public class Display implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private final int height;
    private final int width;
    private final boolean border;

    public Display(int height, int width, boolean border) {
        this.height = height;
        this.width = width;
        this.border = border;
    }

    protected Display() {
        this(0, 0, false);
    }


    public int height() {
        return height;
    }

    public int width() {
        return width;
    }

    public boolean border() {
        return border;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Display) obj;
        return this.height == that.height &&
                this.width == that.width &&
                this.border == that.border;
    }

    @Override
    public int hashCode() {
        return Objects.hash(height, width, border);
    }

    @Override
    public String toString() {
        return "Display[" +
                "height=" + height + ", " +
                "width=" + width + ", " +
                "border=" + border + ']';
    }
}
