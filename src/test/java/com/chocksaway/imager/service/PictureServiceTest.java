package com.chocksaway.imager.service;

import com.chocksaway.imager.entities.Picture;
import com.chocksaway.imager.entities.User;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

public class PictureServiceTest {
    PictureService pictureService = new PictureService();

    @Test
    public void testGetPicture() {
        ResponseEntity<Picture> response = pictureService.getPicture("picture1");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("picture1", response.getBody().getName());
    }

    @Test
    public void testPictureNotFound() {
        ResponseEntity<Picture> response = pictureService.getPicture("notFound");
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testGetPictureWithName() {
        ResponseEntity<User> response = pictureService.getPictureWithName("user1", "picture1");
        User responseBody = response.getBody();
        assertNotNull(responseBody);
        assertEquals("user1", responseBody.getUsername());
        assertEquals("picture1", responseBody.getPictures().getFirst().getName());
    }

    @Test
    public void testGetPictureWithNameNotFound() {
        ResponseEntity<User> response = pictureService.getPictureWithName("user11", "picture1");
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}