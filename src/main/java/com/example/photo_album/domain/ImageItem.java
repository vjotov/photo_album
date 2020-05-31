package com.example.photo_album.domain;

public interface ImageItem {
    Long getId();

    String getName();

    void setId(Long id);

    void setName(String name);
}
