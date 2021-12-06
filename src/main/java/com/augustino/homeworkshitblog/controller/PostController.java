package com.augustino.homeworkshitblog.controller;


import com.augustino.homeworkshitblog.model.Post;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
@RequestMapping("post")
public class PostController {

    @PostMapping
    public String postPost(@ModelAttribute Post post){

        System.out.println(post.getTitle());
        System.out.println(post.getSecondTitle());
        System.out.println(post.getText());
        return "redirect:/blog";
    }

}
