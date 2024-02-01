package com.chocksaway.imager.controller;

import ch.qos.logback.classic.Logger;

import com.chocksaway.imager.entity.Description;
import com.chocksaway.imager.entity.Display;
import com.chocksaway.imager.entity.Image;
import com.chocksaway.imager.service.ImageService;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

@Controller
public class UploadController {
    private final ImageService imageService;

    private static final Logger logger
            = (Logger) LoggerFactory.getLogger(UploadController.class);


    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/src/main/resources/static/images";
    // /home/milesd/workspace/Imager


    public UploadController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping("/uploadimage") public String displayUploadForm() {
        return "index";
    }

    @PostMapping("/upload") public String uploadImage(Model model,
                                                      @RequestParam("image") MultipartFile file,
                                                      @ModelAttribute Description description) {
        var fileNames = new StringBuilder();

        if (!Objects.requireNonNull(file.getOriginalFilename()).isEmpty()) {
            var fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, file.getOriginalFilename());
            fileNames.append(file.getOriginalFilename());
            try {
                Files.write(fileNameAndPath, file.getBytes());
            } catch (IOException ioe) {
                logger.error("File Upload Error: {}", ioe.getMessage());
                throw new RuntimeException(ioe);
            }
            model.addAttribute("msg", "Uploaded images: " + fileNames);

            var image = new Image(fileNames.toString(), description, new Display(50,50,false));
            imageService.save(image);

            var imageIdList = imageService.findAll();
            model.addAttribute("imageIdList", imageIdList);

        }
        return "index";
    }
}
