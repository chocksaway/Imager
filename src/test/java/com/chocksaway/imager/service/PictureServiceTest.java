package com.chocksaway.imager.service;

import com.chocksaway.imager.entities.Picture;
import com.chocksaway.imager.entities.User;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PictureServiceTest {
    PictureService pictureService = new PictureService();

     @Test
     public void testGetPicture() {
         var picture = pictureService.getPicture("picture1");
         assertTrue(picture.isPresent());
         assertEquals("picture1", picture.get().getName());
     }

     @Test
     public void testPictureNotFound() {
         var picture = pictureService.getPicture("notFound");
         assertTrue(picture.isEmpty());
     }

    @Test
    public void testGetPictureWithName() {
        Optional<Map<User, Picture>> result = pictureService.getPictureWithName("user1", "picture1");

        assertTrue(result.isPresent());
        assertEquals(1, result.get().size());

        var user = result.get().keySet().iterator().next();

        var picture = result.get().values().iterator().next();

        assertEquals("user1", user.getUsername());
        assertEquals("picture1", picture.getName());
    }

}
