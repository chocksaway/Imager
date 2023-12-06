package com.chocksaway.imager;

import com.chocksaway.imager.entity.Description;
import com.chocksaway.imager.entity.Image;
import com.chocksaway.imager.repository.ImageRepository;
import org.junit.jupiter.api.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
class ImageRepositoryTest {
    @Autowired
    private ImageRepository imageRepository;
    @Test
    public void whenFindById_thenReturnEmployee() {
        Image myImage = new Image("001", new Description("Image 001"));
        imageRepository.save(myImage);

        Optional<Image> foundImageById = imageRepository.findById(1L);
        Image foundImageByName = imageRepository.findByName(myImage.getName());

        assertThat(foundImageById.isPresent()).isTrue();
        assertThat(foundImageById.get().getName())
                .isEqualTo(myImage.getName());

        assertThat(foundImageByName.getName())
                .isEqualTo(myImage.getName());
    }
}
