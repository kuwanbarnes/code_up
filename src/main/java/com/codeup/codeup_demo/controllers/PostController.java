package com.codeup.codeup_demo.controllers;

import com.codeup.codeup_demo.models.Image;
import com.codeup.codeup_demo.models.Post;
import com.codeup.codeup_demo.models.User;

import com.codeup.codeup_demo.repo.ImageRepo;
import com.codeup.codeup_demo.repo.PostRepository;
import com.codeup.codeup_demo.repo.UserRepo;
import com.codeup.codeup_demo.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {

//    private final PostRepository postDao;
//
//    public PostController(PostRepository postDao){
//        this.postDao = postDao;
//    }
    @Autowired
    private EmailService emailService;
    private final PostRepository postDAO;
private  final UserRepo userDAO;
private final ImageRepo imageRepo;

    public PostController(PostRepository postDAO, UserRepo userDAO, ImageRepo imageRepo){
        this.postDAO = postDAO;
        this.userDAO= userDAO;

        this.imageRepo = imageRepo;
    }

//    List<Post> post =new ArrayList<>();

    @GetMapping("/posts")
    public String SeeAllPostPage(Model model) {
      List<Post> postFromDb= postDAO.findAll();
        model.addAttribute("posts",postFromDb);

        return "posts/index";
    }
    @PostMapping("/posts")
    public String index(Model model) {
        List<Post> postFromDb= postDAO.findAll();
        model.addAttribute("posts",postFromDb);

        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String showOnePost(@PathVariable Long id, Model vModel){
        vModel.addAttribute("post", postDAO.getOne(id));
        return "posts/show";
    }

    @GetMapping("/posts/create")
    public String postViewForm(Model model) {
        model.addAttribute("post", new Post());
        return "posts/create";
    }


    @PostMapping("/posts/create")

    public String createPostForm(@ModelAttribute Post post) {

        User user= (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        post.setOwner(user);
//        Image imagesToSave= new Image(image0);
//        Image image1ToSave= new Image(image1);
//        imagesToSave.setPost(post);
//        image1ToSave.setPost(post);
//      post.setImages(imagesToSave);

        Post savePost= postDAO.save(post);
//        imageRepo.save(imagesToSave);
//        imageRepo.save(image1ToSave);
        emailService.prepareAndSend(savePost, "new Post","hey where are u");
        return "redirect:/posts";
    }

    @GetMapping(path = "/posts/{id}/edit")
    public String updatePost(@PathVariable Long id ,Model model){

        model.addAttribute("post",postDAO.getOne(id));
        return "posts/create";
    }
    @PostMapping(path = "/posts/{id}/edit")

        public String updatePostForm(@PathVariable Long id ,@ModelAttribute Post post) {
            User user= (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            post.setId(id);
            post.setOwner(user);
            postDAO.save(post);
            return "redirect:/posts";
    }



    @PostMapping("/posts/{id}/delete")

    public String DeletePost(@PathVariable Long id) {
        postDAO.deleteById(id);
        return "redirect:/posts";

    }

}
