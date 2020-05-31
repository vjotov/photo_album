package com.example.photo_album.service;

import com.example.photo_album.domain.Album;
import com.example.photo_album.dto.AlbumDto;
import com.example.photo_album.repo.AlbumRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumService {
    private AlbumRepo albumRepo;

    public AlbumService(AlbumRepo albumRepo) {
        this.albumRepo = albumRepo;
    }

    public void create(Album album) {
        albumRepo.save(album);
    }

    public Page<Album> getAllAlbums(Pageable pageable) {
        return albumRepo.findAll(pageable);
    }

    public void updateAlbum(Album albumFromDb, Album album) {
        albumFromDb.setName(album.getName());
        albumRepo.save(albumFromDb);
    }

    public void delete(Album album) {
        // TODO cascade
        albumRepo.delete(album);
    }
}
