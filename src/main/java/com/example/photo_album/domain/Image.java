package com.example.photo_album.domain;

import com.example.photo_album.util.EntityIdResolver;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import javax.persistence.*;

@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        scope = Image.class,
        resolver = EntityIdResolver.class,
        property = "id"
)
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(Views.Id.class)
    private Long id;
    @JsonView(Views.IdName.class)
    private String name;
    @JsonView(Views.FullImage.class)
    private String filename;

    @ManyToOne
    @JoinColumn(name = "album_id")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonView(Views.IdName.class)
    private Album album;

    public Image(Long id, String name, String filename) {
        this.id = id;
        this.name = name;
        this.filename = filename;
    }

    public Image() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }


}
