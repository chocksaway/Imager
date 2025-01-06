package com.chocksaway.imager.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
public class Gallery implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    @Size(min = 10, max = 255, message="The description size is incorrect")
    private String description;
    private LocalDate targetDate;
    private boolean done;
    private String photoId = null;
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
}
