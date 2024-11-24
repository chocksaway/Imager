package com.chocksaway.imager.service;

import com.chocksaway.imager.entities.Picture;
import com.chocksaway.imager.entities.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PictureService {
    private static final List<User> users = List.of(
            User.builder().username("user1").build(),
            User.builder().username("user2").build(),
            User.builder().username("user3").build()
    );

    private static final List<Picture> pictures = List.of(
            Picture.builder().name("picture1").build(),
            Picture.builder().name("picture2").build(),
            Picture.builder().name("picture3").build()
    );

    public ResponseEntity<Picture> getPicture(String name) {
        // Get the picture
        final var picture = pictures.stream()
                .filter(p -> p.getName().equals(name))
                .findFirst();

        return picture.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<Map<User, Picture>> getPictureWithName(String userName, String pictureName) {
        final var user = users.stream()
                .filter(u -> u.getUsername().equals(userName))
                .findFirst();

        if (user.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        final var picture = pictures.stream()
                .filter(p -> p.getName().equals(pictureName))
                .findFirst();

        return picture.map(value -> new ResponseEntity<>(Map.of(user.get(), value), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
