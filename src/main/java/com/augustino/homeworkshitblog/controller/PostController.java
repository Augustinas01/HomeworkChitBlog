package com.augustino.homeworkshitblog.controller;


import com.augustino.homeworkshitblog.model.Post;
import com.augustino.homeworkshitblog.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("post")
public class PostController {


    @Autowired
    PostService postService;

    @PostMapping
    public String postPost(@ModelAttribute Post post){

        postService.createPost(post);

        return "redirect:/blog";
    }

}
