package com.example.photo_album.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
@ApiModel(description = "Details about Album")
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(Views.Id.class)
    @ApiModelProperty(notes="The unique is of the album")
    private Long id;
    //    @NotBlank(message = "Please fill the album name")
    @JsonView(Views.IdName.class)
    @ApiModelProperty(notes = "The album's name")
    private String name;

    public Album(Long id, String name) {
        this.id = id;
        this.name = name;
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
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
}
