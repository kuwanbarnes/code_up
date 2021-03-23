package com.codeup.codeup_demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {
    @GetMapping("/posts")
    @ResponseBody
    public String indexPage() {
        return "posts index page";
    }

    @GetMapping("/posts/{id}")
    @ResponseBody
    public String individualPost(@PathVariable int id) {
        return "view an individual post for id: "+id;
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
