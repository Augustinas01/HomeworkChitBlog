package com.augustino.homeworkshitblog.model;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class Post {

    private String title;
    private String secondTitle;
    private String text;
    private MultipartFile image;
}
