package com.chocksaway.imager.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Gallery implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JsonProperty("username")
    private String username;
    @Size(min = 10, max = 255, message="The description size is incorrect")

    @JsonProperty("description")
    private String description;

    @JsonProperty("targetDate")
    private LocalDate targetDate;

    @JsonProperty("done")
    private boolean done;

    @OneToMany(mappedBy = "gallery", cascade = CascadeType.ALL)
    private List<Picture> pictures;

    public Gallery(String username, String description, LocalDate targetDate, boolean done) {
        this.username = username;
        this.description = description;
        this.targetDate = targetDate;
        this.done = done;
    }

    public Gallery() {
    }

//    private String photoId = null;
    @Serial
    private static final long serialVersionUID = 1L;

    public String toString() {
        return "Gallery{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", description='" + description + '\'' +
                ", targetDate=" + targetDate +
                ", done=" + done +
                '}';
    }

    public String getDescription() {
        return this.description;
    }

    public LocalDate getTargetDate() {
        return this.targetDate;
    }

    public String getUsername() {
        return this.username;
    }
}
