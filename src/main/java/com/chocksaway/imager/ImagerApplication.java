package com.chocksaway.imager;

import com.chocksaway.imager.entity.Description;
import com.chocksaway.imager.entity.Display;
import com.chocksaway.imager.entity.Image;
import com.chocksaway.imager.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class ImagerApplication {
    @Autowired
    ImageRepository ImageRepository;


    public static void main(String[] args) {
        SpringApplication.run(ImagerApplication.class, args);
    }

    @Bean
    @ConditionalOnProperty(prefix = "app", name = "db.init.enabled", havingValue = "true")
    public CommandLineRunner demoCommandLineRunner() {
        return args -> {

            System.out.println("Running.....");

            Image image1 = new Image("Image 1",
                    new Description("Image 1"), new Display(0,0,false));

            Image image2 = new Image("Image 2",
                    new Description("Image 2"), new Display(0,0,false));

            Image image3 = new Image("Image 3",
                    new Description("Image 3"), new Display(0,0,false));

            Image image4 = new Image("Image 4",
                    new Description("Image 4"), new Display(0,0,false));
            ImageRepository.saveAll(List.of(image1, image2, image3, image4));
        };
    }
}
