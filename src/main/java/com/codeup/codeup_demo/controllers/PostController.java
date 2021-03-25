package com.codeup.codeup_demo.controllers;

import com.codeup.codeup_demo.dao.Post;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {
    @GetMapping("/posts")
    @ResponseBody
    public String indexPage() {
        List<Post> PostList = new ArrayList<>();
        Post newpost1= new Post();
        newpost1.setTitle("idk");
        newpost1.setBody("harry lady please shave");;
        PostList.add(newpost1);
        return "index";
    }

    @GetMapping("/posts/{id}")
    @ResponseBody
    public String individualPost(@PathVariable int id) {
        Post newpost = new Post();
        newpost.setTitle("idk");
        newpost.setBody("harry lady please shave");

        return "show"+id;
    }
    @GetMapping("/posts/create")
    @ResponseBody
    public String postViewForm() {
        return "the form for creating a post";
    }

    @PostMapping(path = "/post/create")
    @ResponseBody
    public String createPost() {
        return "you will submit you post here";
        //code
    }
}
