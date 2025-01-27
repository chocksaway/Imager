package com.chocksaway.imager.repository;

import com.chocksaway.imager.entities.Picture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PictureRepository extends JpaRepository<Picture, Long> {
    Optional<Picture> findByName(String name);
}

