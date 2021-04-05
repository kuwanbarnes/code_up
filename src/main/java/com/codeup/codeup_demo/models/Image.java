package com.codeup.codeup_demo.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "images")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 225,nullable = false)
    private String image;

    public Image() {
    }

    public Image(Long id, String image) {
        this.id = id;
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @ManyToMany(mappedBy = "images")
    private List<Post> postList;
}


