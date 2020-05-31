package com.example.photo_album.controller;

import com.example.photo_album.domain.Album;
import com.example.photo_album.domain.Image;
import com.example.photo_album.service.ImageService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/api")
public class ImageController {
    private ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping("/album/{id}/upload")
    public void uploadImage(@PathVariable("id") Album album,
                            @RequestParam("imageName") String imageName,
                            @RequestParam("file") MultipartFile file) {
        imageService.addImage(imageName, album, file);
    }

    @GetMapping("/image/{id}")
    public Image getImage(@PathVariable("id") Image image) {
        return image;
    }

    @GetMapping(
            value = "/image/{id}/download",
            produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] downloadImage(@PathVariable("id") Image image) {
        if(null == image) return null;
        try {
            return imageService.downloadImage(image);

        } catch (IOException ex) {
            return null;
        }
    }

    @DeleteMapping("/image/{id}")
    public void deleteImage(@PathVariable("id") Image image) {
        imageService.deleteImage(image);
    }


}
