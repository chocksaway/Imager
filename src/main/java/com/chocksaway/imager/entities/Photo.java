package com.chocksaway.imager.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@Document(collection = "photos")
public class Photo {
    public Photo(String name) {
        this.name = name;
    }
    @Id
    private String id;

    private String name;

    private Binary image;
}
