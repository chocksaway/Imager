//package com.chocksaway.imager.service;
//
//import com.chocksaway.imager.entities.Gallery;
//import org.junit.jupiter.api.Test;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//
//public class GalleryTest {
//    GalleryService galleryService = new GalleryService();
//
//    @Test
//    public void testGetGallery() {
//        List<Gallery> gallery = galleryService.findByUsername("milesd");
//        assertNotNull(gallery);
//        assertEquals(5, gallery.size());
//    }
//}