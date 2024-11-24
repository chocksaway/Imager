package com.chocksaway.imager.service;

import com.chocksaway.imager.entities.Picture;
import com.chocksaway.imager.entities.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public ResponseEntity<User> getPictureWithName(String userName, String pictureName) {
        Optional<User> user = users.stream()
                .filter(u -> u.getUsername().equals(userName))
                .findFirst();

        Optional<Picture> picture = pictures.stream()
                .filter(p -> p.getName().equals(pictureName))
                .findFirst();

        if (user.isPresent() && picture.isPresent()) {
            List<Picture> pictureList = List.of(picture.get());
            return new ResponseEntity<>(User.builder()
                    .username(user.get().getUsername())
                    .pictures(pictureList).build(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
