package com.chocksaway.imager.service;

import com.chocksaway.imager.entity.Image;
import com.chocksaway.imager.repository.ImageRepository;
import org.springframework.stereotype.Service;

@Service
public class ImageService {
    private final ImageRepository imageRepository;

    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public void save(Image image) {
        imageRepository.save(image);
    }


    public Iterable<Image> findAll() {
        return imageRepository.findAll();
    }
}
