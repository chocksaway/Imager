package com.chocksaway.imager.service;

import com.chocksaway.imager.entities.Gallery;
import com.chocksaway.imager.entities.Picture;
import com.chocksaway.imager.repository.GalleryRepository;
import com.chocksaway.imager.repository.PictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;

@Service
public class DataLoaderService {

    private final GalleryRepository galleryRepository;
    private final PictureRepository pictureRepository;

    @Autowired
    public DataLoaderService(GalleryRepository galleryRepository, PictureRepository pictureRepository) {
        this.galleryRepository = galleryRepository;
        this.pictureRepository = pictureRepository;
    }

    public void loadInitialData() {

        Gallery gallery1 = new Gallery("milesd", "Gallery 1 so there !!!!!!!!!!!!!", LocalDate.now(), false);
        Gallery gallery2 = new Gallery("milesd", "Gallery 2 so there !!!!!!!!!!!!!", LocalDate.now(), false);
        Gallery gallery3 = new Gallery("milesd", "Gallery 3 so there !!!!!!!!!!!!!", LocalDate.now(), false);
        Gallery gallery4 = new Gallery("milesd", "Gallery 4 so there !!!!!!!!!!!!!", LocalDate.now(), false);
        Gallery gallery5 = new Gallery("milesd", "Gallery 5 so there !!!!!!!!!!!!!", LocalDate.now(), false);


        Picture picture1 = new Picture(gallery1, "picture 1", 100, 100, "picture 1");
        Picture picture2 = new Picture(gallery1, "picture 2", 100, 100, "picture 2");
        Picture picture3 = new Picture(gallery1, "picture 3", 100, 100, "picture 3");
        Picture picture4 = new Picture(gallery1, "picture 4", 100, 100, "picture 4");
        Picture picture5 = new Picture(gallery1, "picture 5", 100, 100, "picture 5");
        Picture picture6 = new Picture(gallery1, "picture 6", 100, 100, "picture 6");
        Picture picture7 = new Picture(gallery1, "picture 7", 100, 100, "picture 7");
        Picture picture8 = new Picture(gallery1, "picture 8", 100, 100, "picture 8");
        Picture picture9 = new Picture(gallery1, "picture 9", 100, 100, "picture 9");
        Picture picture10 = new Picture(gallery1, "picture 10", 100, 100, "picture 10");


        galleryRepository.saveAll(Arrays.asList(
                gallery1, gallery2, gallery3, gallery4, gallery5
        ));

        pictureRepository.saveAll(Arrays.asList(
                picture1, picture2, picture3, picture4, picture5,
                picture6, picture7, picture8, picture9, picture10
        ));
    }
}