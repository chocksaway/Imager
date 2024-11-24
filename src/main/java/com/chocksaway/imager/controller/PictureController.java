package com.chocksaway.imager.controller;

import com.chocksaway.imager.entities.Picture;
import com.chocksaway.imager.service.PictureService;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class PictureController {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(PictureController.class);
    private final PictureService pictureService;

    public PictureController(PictureService pictureService) {
        this.pictureService = pictureService;
    }

    @GetMapping(value = "/picture", produces = "application/json")
    public ResponseEntity<Picture> showUpdateTodoPage(@RequestParam String name, ModelMap model) {
        logger.info("Received request for picture with name: {}", name);
        Optional<Picture> picture = pictureService.getPicture(name);
        if (picture.isPresent()) {
            logger.info("Picture found: {}", picture);
            return new ResponseEntity<>(picture.get(), HttpStatus.OK);
        } else {
            logger.warn("Picture not found for name: {}", name);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
