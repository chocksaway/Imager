package com.chocksaway.imager.service;

import com.chocksaway.imager.entities.Photo;
import com.chocksaway.imager.repository.PhotoRepository;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class PhotoService {
    private final PhotoRepository repository;

    public PhotoService(PhotoRepository repository) {
        this.repository = repository;
    }

    public String addPhoto(String title, MultipartFile file) throws IOException {
        Photo photo = Photo.builder()
                .title(title)
                .image(new Binary(BsonBinarySubType.BINARY, file.getBytes()))
                .build();

        photo = repository.insert(photo);
        return photo.getId();
    }

    public Optional<Photo> getPhoto(String id) {
        return repository.findById(id).stream().findFirst();
    }
}