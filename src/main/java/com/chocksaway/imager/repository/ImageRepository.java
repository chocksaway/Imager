package com.chocksaway.imager.repository;

import com.chocksaway.imager.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
    Image findByName(String name);
}
