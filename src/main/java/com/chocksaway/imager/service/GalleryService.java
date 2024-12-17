package com.chocksaway.imager.service;

import com.chocksaway.imager.domain.Gallery;
import jakarta.validation.Valid;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@Service
public class GalleryService {

    private static final List<Gallery> GalleryList = new ArrayList<>();
    public static int GalleryCount;

    static {
        GalleryList.add(Gallery.builder().id(0).username("milesd").description("Learn AWS").build());

        GalleryList.add(Gallery.builder().id(1).username("milesd").description("Learn AWS 12345").build());

        GalleryList.add(Gallery.builder().id(2).username("milesd").description("Learn DevOps").build());

        GalleryList.add(Gallery.builder().id(3).username("milesd").description("Learn full stack development").build());

        GalleryList.add(Gallery.builder().id(4).username("milesd").description("Learn AWS in 5 minutes").build());

        GalleryCount = GalleryList.size();
    }

    public List<Gallery> findByUsername(String username) {
        if (username == null) {
            return new ArrayList<>();
        }
        return GalleryList.stream()
                .filter(each ->
                        each.getUsername().equals(username)).toList();
    }

    public void addGallery(String username, String description, LocalDate localDate, boolean done, String photoId) {
        var gallery = Gallery.builder().username(username)
                .id(++GalleryCount)
                .description(description)
                .targetDate(localDate)
                .done(done)
                .photoId(photoId)
                .build();

        GalleryList.add(gallery);
    }

    public void deleteById(int id) {
        Predicate<? super Gallery> predicate =
                each -> each.getId() == id;
        GalleryList.removeIf(predicate);
    }

    @Cacheable(
            value = "GalleryCache",
            key = "#id",
            condition = "#id>10")
    public Gallery findById(int id) {
        Predicate<? super Gallery> predicate =
                each -> each.getId() == id;
        Optional<Gallery> GalleryOptional = GalleryList.stream()
                .filter(predicate)
                .findFirst();
        return GalleryOptional.orElse(null);

    }

    public void updateGallery(@Valid Gallery Gallery) {
        deleteById(Gallery.getId());
        GalleryList.add(Gallery);
    }
}
