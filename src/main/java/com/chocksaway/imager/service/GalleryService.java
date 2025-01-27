package com.chocksaway.imager.service;

import com.chocksaway.imager.entities.Gallery;
import com.chocksaway.imager.repository.GalleryRepository;
import jakarta.validation.Valid;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class GalleryService {

    private final GalleryRepository galleryRepository;

    public GalleryService(GalleryRepository galleryRepository) {
        this.galleryRepository = galleryRepository;
    }

    public List<Gallery> findByPictureId(Long pictureId) {
        return galleryRepository.findByPictureId(pictureId);
    }

    public List<Gallery> findByUsername(String username) {
        return galleryRepository.findByUsername(username);
    }

    public void addGallery(String username, String description, LocalDate localDate, boolean done, String photoId) {
        Gallery gallery = new Gallery(username, description, localDate, done);

        galleryRepository.save(gallery);
    }

    public void deleteById(long id) {
        galleryRepository.deleteById(id);
    }

    @Cacheable(
            value = "GalleryCache",
            key = "#id",
            condition = "#id>10")
    public Gallery findById(long id) {
        return galleryRepository.findById(id).orElse(null);
    }

    public void updateGallery(@Valid Gallery gallery) {
        galleryRepository.save(gallery);
    }
}