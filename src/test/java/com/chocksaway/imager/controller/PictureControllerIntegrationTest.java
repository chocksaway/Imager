package com.chocksaway.imager.controller;

import com.chocksaway.imager.entities.Picture;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PictureControllerIntegrationTest {

    @LocalServerPort
    private int port;

    private final TestRestTemplate restTemplate = new TestRestTemplate("milesd", "milesd", TestRestTemplate.HttpClientOption.ENABLE_COOKIES);

    @Test
    public void testGetPicture() {
        String url = "http://localhost:" + port + "/picture?name=picture1";
        ResponseEntity<Picture> response = restTemplate.getForEntity(url, Picture.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("picture1", response.getBody().getName());
    }
}
