package com.example.photo_album.repo;

import com.example.photo_album.domain.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepo extends JpaRepository<Image, Long> {
}
