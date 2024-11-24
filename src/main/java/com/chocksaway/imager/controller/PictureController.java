package com.chocksaway.imager.controller;

import com.chocksaway.imager.entities.Picture;
import com.chocksaway.imager.service.PictureService;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@RestController
public class PictureController {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(PictureController.class);
    private final PictureService pictureService;

    public PictureController(PictureService pictureService) {
        this.pictureService = pictureService;
    }

    @GetMapping(value = "/picture", produces = "application/json")
    public ResponseEntity<Picture> getPicture(@RequestParam String name, ModelMap model) {
        logger.info("Received request for picture with name: {}", name);
        return pictureService.getPicture(name);
    }
}
