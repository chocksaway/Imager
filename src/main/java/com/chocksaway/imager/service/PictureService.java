package com.chocksaway.imager.service;

import com.chocksaway.imager.entities.Picture;
import org.springframework.stereotype.Service;

@Service
public class PictureService {
    public Picture getPicture(String name) {
        // Get the picture
        return Picture.builder()
                .name("myPicture")
                .height(100)
                .width(100)
                .url("")
                .build();
    }
}
