package com.augustino.homeworkshitblog.controller;

import com.augustino.homeworkshitblog.entities.UserEntity;
import com.augustino.homeworkshitblog.repository.UserRepository;
import com.augustino.homeworkshitblog.repository.RoleRepository;
import com.augustino.homeworkshitblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("signup")
public class SignupController {

    @Autowired
    UserService userService;



    @GetMapping
    public String get(Model model){

        model.addAttribute("user", new UserEntity());

        return "signup";
    }

    @PostMapping
    public String post(@ModelAttribute UserEntity user){

        userService.createUser(user);
        return "redirect:/index";
    }




}
