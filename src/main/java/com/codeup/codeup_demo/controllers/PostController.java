package com.codeup.codeup_demo.controllers;

import com.codeup.codeup_demo.models.Post;
import com.codeup.codeup_demo.repo.PostRepository;
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

    public PostController(PostRepository postDAO){
        this.postDAO = postDAO;
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
        List<Post> deletePostFromDb= postDAO.deleteByTitle("hello");
        model.addAttribute("posts",postFromDb);
        model.addAttribute("delete",deletePostFromDb);
        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String individualPost(@PathVariable int id, Model model) {
        List<Post> postFromDb= postDAO.findById(id);
        model.addAttribute("post",new Post("ipad","like new"));
        return "posts/show";
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

    @PostMapping("/posts/delete")
    public ModelAndView DeleteUser(@ModelAttribute("postID") Post
                                           post) {
        ModelAndView mav = new ModelAndView();

        System.err.println("Deleting:");
        System.err.println("getId " + post.getId());
        System.err.println("getTitle " + post.getTitle();
        System.err.println("getCourse " + post.getBody();
        postDAO.d(Customers.getId());
        customersService.listAllCustomers();
        mav.setViewName("redirect:/" + MAIN_VIEW);
        return mav;

    }

}
