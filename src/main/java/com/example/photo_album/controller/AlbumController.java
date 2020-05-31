package com.example.photo_album.controller;

import com.example.photo_album.domain.Album;
import com.example.photo_album.domain.Views;
import com.example.photo_album.service.AlbumService;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@Api(value = "API value", description = "Api description") 
public class AlbumController {
    private AlbumService albumService;

    @Autowired
    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @PostMapping("/album")
    @ApiOperation(value = "Creates an Album",
    notes = "The note")
    public void createAlbum(@ApiParam(value = "All parameters of the new album", required = true) @Valid @RequestBody Album album) {
        albumService.create(album);
    }

    @GetMapping("/album/list")
    //@JsonView(Views.Id.class)
    public @ResponseBody Page<Album> lisAlbums(@PageableDefault Pageable pageable) {
        return albumService.getAllAlbums(pageable);
    }

    @GetMapping("/album/{id}")
    public Album browseAlbum(@PathVariable("id") Album album) {
        return album;
    }

    @PutMapping("/album/{id}")
    public void renameAlbum(@PathVariable("id") Album albumFromDb, @RequestBody Album album) {
        albumService.updateAlbum(albumFromDb, album);
    }

    @DeleteMapping("/album/{id}")
    public void deleteAlbum(@PathVariable("id") Album album) {
        albumService.delete(album);
    }


}
