package com.example.photo_album.domain;

public final class Views {
    public interface Id {}

    public interface IdName extends Id {}

    public interface FullImage extends IdName {}

    public interface FullAlbum extends IdName {}
}
