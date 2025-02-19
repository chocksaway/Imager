package com.chocksaway.imager.repository;

import com.chocksaway.imager.entities.Gallery;
import com.chocksaway.imager.entities.Photo;
import com.chocksaway.imager.entities.Picture;

import config.TestConfig;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@DataJpaTest
@ContextConfiguration(classes = TestConfig.class)
public class PictureRepositoryTest {
    @Autowired
    PictureRepository pictureRepository;

    @Autowired
    GalleryRepository galleryRepository;

    @Mock
    private PhotoRepository photoRepository;

    @BeforeEach
    public void setUp() {
        Gallery gallery1 = new Gallery("miles", "Gallery 1 so there !!!!!!!!!!!!!", LocalDate.now(), false);
        galleryRepository.save(gallery1);

        Picture picture1 = new Picture(gallery1, "picture1", 100, 100, "picture 1");
        pictureRepository.save(picture1);
    }

    @AfterEach
    public void tearDown() {
        pictureRepository.deleteAll();
        galleryRepository.deleteAll();
    }

    @Test
    public void testGetPicture() {
        final Optional<Picture> picture = pictureRepository.findByName("picture1");
        assertTrue(picture.isPresent());
        assertEquals("picture1", picture.get().getName());
    }

    @Test
    public void testPictureNotFound() {
        final Optional<Picture> picture = pictureRepository.findByName("notFound");
        assertFalse(picture.isPresent());
    }

    @Test
    public void testGetPictureWithName() {

        assertEquals(1, galleryRepository.findByUsername("miles").stream()
                .filter(each -> each.getUsername().equals("miles")).count());

        pictureRepository.findByName("picture1").ifPresent(picture -> {
            assertNotNull(picture);
            assertEquals("picture1", picture.getName());
        });
    }

    @Test
    public void testPhotoExistsByUsingPictureName() {
        Photo photo = new Photo();
        photo.setName("2cv");
        when(photoRepository.findByName("2cv")).thenReturn(Optional.of(photo));

        Optional<Photo> retrievedPhoto = photoRepository.findByName("2cv");

        assertTrue(retrievedPhoto.isPresent());
        assertEquals("2cv", retrievedPhoto.get().getName());
    }
}