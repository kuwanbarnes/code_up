package com.codeup.codeup_demo.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "posts")
public class Post {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


@Column( length = 225,nullable = false)
    private String title;

@Column(columnDefinition = "TEXT",length = 3000,nullable = false)
    private String body;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name="post_images",
            joinColumns={@JoinColumn(name="posts_id")},
            inverseJoinColumns={@JoinColumn(name="images_id")}
    )
    private List<Image> images;

@ManyToOne
@JoinColumn (name = "owner_id")
    private User owner;

    public Post(){}

    public Post(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public Post(Long id,String title, String body) {
        this.id=id;
        this.title = title;
        this.body = body;
    }

    public Post(String title, String body, User owner) {
        this.title = title;
        this.body = body;
        this.owner = owner;
    }

    public Post(Long id, String title, String body, List<Image> images, User owner) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.images = images;
        this.owner = owner;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
}
