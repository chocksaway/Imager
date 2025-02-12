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
    private final PhotoRepository photoRepository;

    public PhotoService(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    public String addPhoto(String name, MultipartFile file) throws IOException {
        Photo photo = Photo.builder()
                .name(name)
                .image(new Binary(BsonBinarySubType.BINARY, file.getBytes()))
                .build();

        photo = photoRepository.insert(photo);
        return photo.getId();
    }

    public Optional<Photo> getPhoto(String id) {
        return photoRepository.findById(id).stream().findFirst();
    }
}