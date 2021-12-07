package com.augustino.homeworkshitblog.controller;

import com.augustino.homeworkshitblog.model.Post;
import com.augustino.homeworkshitblog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class BlogController {

    private final PostService postService;


    @GetMapping("blog")
    public String getPage(Model model) {
        model.addAttribute("postCreation", new Post());

        model.addAttribute("allPosts", postService.getAllPostsList());

        return "feed";
    }

}
