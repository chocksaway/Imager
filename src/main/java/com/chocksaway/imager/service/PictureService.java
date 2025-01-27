package com.chocksaway.imager.service;

import com.chocksaway.imager.entities.Gallery;
import com.chocksaway.imager.entities.Picture;
import com.chocksaway.imager.entities.User;
import com.chocksaway.imager.repository.GalleryRepository;
import com.chocksaway.imager.repository.PictureRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PictureService {
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(PictureService.class);

    private final PictureRepository pictureRepository;
    private final GalleryRepository galleryRepository;

    public PictureService(PictureRepository pictureRepository, GalleryRepository galleryRepository) {
        this.pictureRepository = pictureRepository;
        this.galleryRepository = galleryRepository;
    }

    private static final List<User> users = List.of(
            User.builder().username("user1").build(),
            User.builder().username("user2").build(),
            User.builder().username("user3").build()
    );

    private static final List<Picture> pictures = List.of(
            new Picture(null, "picture 1", 100, 100, "picture 1"),
            new Picture(null, "picture 2", 100, 100, "picture 1"),
            new Picture(null, "picture 1", 100, 100, "picture 1"));

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

    public ResponseEntity<Picture> addPicture(Picture picture) {
        Gallery gallery = picture.getGallery();
        if (gallery == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if ((long) galleryRepository.findByPictureId(picture.getId()).size() == 0) {
            galleryRepository.save(gallery);
        }

        final Optional<Picture> findPicture = pictureRepository.findByName(picture.getName());

        if (findPicture.isPresent()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(pictureRepository.save(picture), HttpStatus.OK);
    }
}
