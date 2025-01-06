package com.chocksaway.imager.repository;

import com.chocksaway.imager.entities.Gallery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GalleryRepository extends JpaRepository<Gallery, Integer> {
    List<Gallery> findByUsername(String username);
}