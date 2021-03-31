package com.codeup.codeup_demo.controllers;

import com.codeup.codeup_demo.models.Post;
import com.codeup.codeup_demo.models.User;
import com.codeup.codeup_demo.repo.PostRepository;
import com.codeup.codeup_demo.repo.UserRepo;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {

//    private final PostRepository postDao;
//
//    public PostController(PostRepository postDao){
//        this.postDao = postDao;
//    }
    private final PostRepository postDAO;
private  final UserRepo userDAO;
    public PostController(PostRepository postDAO,UserRepo userDAO){
        this.postDAO = postDAO;
        this.userDAO= userDAO;
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

        User user= userDAO.getOne(2L);

        post.setOwner(user);
        postDAO.save(post);
        return "redirect:/posts";
    }

    @GetMapping(path = "/posts/{id}/update")
    public String updatePost(@PathVariable Long id ,Model model){
        Post postfromdb=postDAO.getOne(id);

        model.addAttribute("post",postfromdb);
        return "posts/edit";
    }
    @PostMapping(path = "/posts/{id}/update")
    @ResponseBody
        public String updatePostForm(@PathVariable Long id ,@ModelAttribute Post post) {
            User user= userDAO.getOne(2L);
            post.setId(id);
            post.setOwner(user);
            postDAO.save(post);
            return "you updated post";
    }



    @PostMapping("/posts/{id}/delete")
    @ResponseBody
    public String DeletePost(@PathVariable Long id) {
        postDAO.deleteById(id);
        return "You delete post";

    }

}
