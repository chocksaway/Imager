package com.chocksaway.imager.controller;

import com.chocksaway.imager.entities.Gallery;
import com.chocksaway.imager.service.GalleryService;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/galleries/")
public class GalleryRestController {
    private final GalleryService galleryService;

    public GalleryRestController(GalleryService galleryService) {
        this.galleryService = galleryService;
    }

    @GetMapping("/{pictureId}")
    public List<Gallery> getGalleryById(@PathVariable Long pictureId) {
        return galleryService.findByPictureId(pictureId);
    }

    @RequestMapping(value ="", method = RequestMethod.GET)
    public String showUpdateGalleryPage(@RequestParam int id, ModelMap model) {
        Gallery gallery = galleryService.findById(id);
        model.addAttribute("gallery", gallery);
        return "add-gallery";
    }


}
