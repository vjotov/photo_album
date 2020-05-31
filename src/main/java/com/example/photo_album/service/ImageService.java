package com.example.photo_album.service;

import com.example.photo_album.domain.Album;
import com.example.photo_album.domain.Image;
import com.example.photo_album.repo.ImageRepo;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service
public class ImageService {
    @Value("${upload.path}")
    private String uploadPath;
    @Autowired
    private ImageRepo imageRepo;

    public void addImage(String imageName, Album album, MultipartFile file) {
        try {
            Image image = new Image();
            image.setName(imageName);
            image.setAlbum(album);
            saveFile(image, file);
            imageRepo.save(image);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void deleteImage(Image image) {
        imageRepo.delete(image);
    }

    private void saveFile(Image image, MultipartFile file) throws IOException {
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFilename));

            image.setFilename(resultFilename);
        }
    }


    public byte[] downloadImage(Image image) throws IOException {
        InputStream in = getClass().getResourceAsStream(image.getFilename());
        return IOUtils.toByteArray(in);
    }
}
