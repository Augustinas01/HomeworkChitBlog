package com.augustino.homeworkshitblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SignupController {

    @GetMapping("signup")
    public String getPage(){
        return "signup";
    }
}
