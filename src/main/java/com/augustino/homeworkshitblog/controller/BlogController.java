package com.augustino.homeworkshitblog.controller;

import com.augustino.homeworkshitblog.model.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BlogController {

    @GetMapping("blog")
    public String getPage(Model model) {
        model.addAttribute("post", new Post());
        return "feed";
    }

}
