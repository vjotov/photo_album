package com.example.photo_album.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(Views.Id.class)
    private Long id;
    //    @NotBlank(message = "Please fill the album name")
    @JsonView(Views.IdName.class)
    private String Name;

    public Album(Long id, String name) {
        this.id = id;
        Name = name;
    }

    @OneToMany(mappedBy = "album", orphanRemoval = true)
    @JsonIdentityReference(alwaysAsId = true)
    @JsonView(Views.FullAlbum.class)
//    @JsonIdentityReference
//    @JsonSerialize(as=ImageItem.class)
    private List<Image> images = new ArrayList<>();

    public Album() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
}
