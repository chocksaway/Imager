package com.chocksaway.imager.repository;

import com.chocksaway.imager.entities.Gallery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GalleryRepository extends JpaRepository<Gallery, Long> {
    List<Gallery> findByUsername(String username);

    @Query("SELECT g FROM Gallery g JOIN g.pictures p WHERE p.id = :pictureId")
    List<Gallery> findByPictureId(@Param("pictureId") Long pictureId);
}