package com.chocksaway.imager.util;

import com.chocksaway.imager.ImagerApplication;
import com.chocksaway.imager.entities.Photo;
import org.bson.types.Binary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ImageLoader {
    private static final Logger logger = LoggerFactory.getLogger(ImagerApplication.class);

    public Photo loadImage(final String name, final String path) {
        Photo photo = new Photo();
        try {
            // Path to the image file
            Path imagePath = Paths.get(path);

            // Read the image file as a byte array
            byte[] imageBytes = Files.readAllBytes(imagePath);

            // Convert the byte array to a Binary object
            Binary imageBinary = new Binary(imageBytes);

            // Create a Photo object and set the image
            photo.setName(name);
            photo.setImage(imageBinary);
        } catch (IOException e) {
            logger.error("Cannot load photo {} ", path);
        }

        return photo;
    }
}
