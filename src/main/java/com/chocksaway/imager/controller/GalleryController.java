package com.chocksaway.imager.controller;

import com.chocksaway.imager.entities.Gallery;
import com.chocksaway.imager.security.SpringSecurityConfiguration;
import com.chocksaway.imager.service.PhotoService;
import com.chocksaway.imager.service.GalleryService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Base64;
import java.util.List;

@Controller
@SessionAttributes("name")
public class GalleryController {
    private final GalleryService galleryService;
    private final PhotoService photoService;

    public GalleryController(PhotoService photoService, GalleryService galleryService) {
        this.photoService = photoService;
        this.galleryService = galleryService;
    }


    @RequestMapping("list-gallery")
    public String listAllGallery(ModelMap model) {
        final var username = SpringSecurityConfiguration.getLoggedInUser();
        List<Gallery> gallery = galleryService.findByUsername(username);
        model.addAttribute("gallery", gallery);

        return "list-gallery";
    }

    @RequestMapping(value ="delete-gallery")
    public String deleteGallery(@RequestParam int id) {
        galleryService.deleteById(id);
        return "redirect:list-gallery";
    }

    @RequestMapping(value ="update-gallery", method = RequestMethod.GET)
    public String showUpdateGalleryPage(@RequestParam int id, ModelMap model) {
        Gallery gallery = galleryService.findById(id);
        model.addAttribute("gallery", gallery);
        return "add-gallery";
    }


    @RequestMapping(value = "/photo", method = RequestMethod.GET)
    public String getPhoto(@RequestParam String id, Model model) {
        final var photo = photoService.getPhoto(id);

        if (photo.isEmpty()) {
            return "view-photo";
        }

        model.addAttribute("image",
                Base64.getEncoder().encodeToString(photo.get().getImage().getData()));

        return "view-photo";
    }




    @RequestMapping(value = "update-gallery", method = RequestMethod.POST)
    public String updateGallery(@RequestParam int id, @Valid Gallery gallery, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            return "add-gallery";
        }

        galleryService.updateGallery(gallery, id);
        return "redirect:list-gallery";
    }


    @RequestMapping(value = "add-gallery", method = RequestMethod.GET)
    public String addGallery(ModelMap model) {
        var username = SpringSecurityConfiguration.getLoggedInUser();
        var gallery = new Gallery(username, "", LocalDate.now(), false);

        model.put("gallery", gallery);
        return "add-gallery";
    }


    @RequestMapping(value = "add-gallery", method = RequestMethod.POST)
    public String addNewGallery(@RequestParam("image") MultipartFile image,
                            @Valid Gallery gallery,
                            BindingResult result,
                            ModelMap model) throws IOException {
        if (result.hasErrors()) {
            return "add-gallery";
        }
        var username = SpringSecurityConfiguration.getLoggedInUser();
        var photoId = photoService.addPhoto(gallery.getDescription(), image);
        galleryService.addGallery(
                username,
                gallery.getDescription(),
                gallery.getTargetDate(),
                false, photoId);

        return "redirect:list-gallery";
    }
}
