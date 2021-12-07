package com.augustino.homeworkshitblog.controller;

import com.augustino.homeworkshitblog.entities.UserEntity;
import com.augustino.homeworkshitblog.model.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HelloController {
    @GetMapping("/")
    public String hello(Model model) {
        model.addAttribute("postCreation", new Post());

        return "index";
    }
}
