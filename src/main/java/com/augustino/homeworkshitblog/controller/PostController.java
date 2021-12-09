package com.augustino.homeworkshitblog.controller;


import com.augustino.homeworkshitblog.model.Comment;
import com.augustino.homeworkshitblog.model.Post;
import com.augustino.homeworkshitblog.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Slf4j
@Controller
@RequestMapping("post")
public class PostController {


    @Value("${postimage.path}")
    private String postImagePath;

    @Autowired
    PostService postService;

    @PostMapping
    public String postPost(@ModelAttribute Post post){

        postService.createPost(post, postImagePath);

        return "redirect:/blog";
    }

    @PostMapping("comment")
    public String commentPost(@ModelAttribute Comment comment){

        postService.commentPost(comment);

        return "redirect:/blog";
    }

    @PostMapping("like")
    public String likePost(@RequestParam("postID") UUID uuid){

        postService.likePost(uuid);

        return "redirect:/blog";
    }

    @PostMapping("delete")
    public String deletePost(@RequestParam("postID") UUID uuid){

        postService.deletePost(uuid);

        return "redirect:/blog";
    }

    @PostMapping("edit")
    public String editPost(@RequestParam("text") UUID uuid){

        postService.deletePost(uuid);

        return "redirect:/blog";
    }

}
