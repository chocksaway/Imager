package com.chocksaway.imager.controller;

import com.chocksaway.imager.entities.Gallery;
import com.chocksaway.imager.entities.Picture;
import com.chocksaway.imager.repository.GalleryRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AddPictureIntegrationTest {

    @Autowired
    private GalleryRepository galleryRepository;

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @Test
    public void testAddPicture() throws JsonProcessingException {
        // Get the gallery populated with data
        Gallery gallery1 = new Gallery("milesd", "Gallery 1 so there !!!!!!!!!!!!!", LocalDate.now(), false);

        Picture picture = new Picture(gallery1, "picture 99", 100, 100, "picture1.jpg");

        System.out.println("tester");

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        String jsonRequest = objectMapper.writeValueAsString(picture);

        // Create HttpHeaders and set the content type
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Create HttpEntity containing the JSON request and headers
        HttpEntity<String> request = new HttpEntity<>(jsonRequest, headers);

        String url = "http://localhost:" + port + "/galleries/picture";

        // Send a POST request and get the response
        ResponseEntity<String> response = restTemplate.exchange(
                url,  // The endpoint you want to call
                HttpMethod.POST,      // HTTP method
                request,              // The entity (including body and headers)
                String.class         // The type of the response body
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}