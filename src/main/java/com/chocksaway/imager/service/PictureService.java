package com.chocksaway.imager.service;

import com.chocksaway.imager.entities.Picture;
import com.chocksaway.imager.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
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

    public Optional<Picture> getPicture(String name) {
        // Get the picture
        return pictures.stream()
                .filter(picture -> picture.getName().equals(name))
                .findFirst();
    }

    public Optional<Map<User, Picture>> getPictureWithName(String userName, String pictureName) {

        final var user = users.stream()
                .filter(u -> u.getUsername()
                    .equals(userName))
                    .findFirst();
        if (user.isEmpty()) {
            return Optional.empty();
        }

        final var picture = pictures.stream()
                .filter(p -> p.getName()
                    .equals(pictureName))
                    .findFirst();

        return picture.map(value -> Map.of(user.get(), value));

    }
}
