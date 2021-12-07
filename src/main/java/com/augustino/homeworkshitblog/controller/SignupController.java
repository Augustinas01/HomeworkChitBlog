package com.augustino.homeworkshitblog.controller;

import com.augustino.homeworkshitblog.entities.UserEntity;
import com.augustino.homeworkshitblog.repository.UserRepository;
import com.augustino.homeworkshitblog.repository.RoleRepository;
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
    UserRepository accountRepository;

    @Autowired
    RoleRepository roleRepository;



    @GetMapping
    public String get(Model model){

        model.addAttribute("user", new UserEntity());
        return "signup";
    }

    @PostMapping
    public String post(@ModelAttribute UserEntity user){

        createUser(user.getName(), user.getPassword());
        return "redirect:/index";
    }



    private void createUser(String name, String password){

        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        UserEntity user = UserEntity.builder()
                .name(name)
                .password(encoder.encode(password))
                .roles(List.of(roleRepository.findByName("ROLE_USER")))
                .build();
        accountRepository.saveAndFlush(user);
    }
}
