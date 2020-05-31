package com.example.photo_album.repo;

import com.example.photo_album.domain.Album;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepo extends JpaRepository<Album, Long> {
    @Override
    @EntityGraph(attributePaths = {"images"})
    Page<Album> findAll(Pageable pageable);
}
