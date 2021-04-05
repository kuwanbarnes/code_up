package com.codeup.codeup_demo.models;

import javax.persistence.*;

@Entity
@Table(name="images")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = true, columnDefinition = "text")
    private String imagePath;

    @ManyToOne
    @JoinColumn (name = "post_id")
    private Post post;

    Image(){}
    public Image(String imagePath){
        this.imagePath=imagePath;
    }
    public Image(String imagePath, Post post){
        this.imagePath=imagePath;
        this.post = post;
    }
    public Image(Long id,String imagePath, Post post){
        this.id=id;
        this.imagePath=imagePath;
        this.post = post;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getimagePath() {
        return imagePath;
    }

    public void setimagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }


}