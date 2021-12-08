package com.augustino.homeworkshitblog.service;

import com.augustino.homeworkshitblog.entities.PostEntity;
import com.augustino.homeworkshitblog.model.Post;
import com.augustino.homeworkshitblog.repository.PostRepository;
import com.augustino.homeworkshitblog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
//    private final UserService userService;


    public void createPost(Post post){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        PostEntity postEntity = PostEntity.builder()
                .title(post.getTitle())
                .secondTitle(post.getSecondTitle())
                .text(post.getText())
                .user(userRepository.findByName(auth.getName()).get())
                .build();

        postRepository.saveAndFlush(postEntity);
    }

    public List<PostEntity> getAllPostsList(){
        return postRepository.findAll();
    }
}
