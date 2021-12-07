package com.augustino.homeworkshitblog.service;

import com.augustino.homeworkshitblog.entities.PostEntity;
import com.augustino.homeworkshitblog.model.Post;
import com.augustino.homeworkshitblog.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserService userService;


    public void createPost(Post post){

        PostEntity postEntity = PostEntity.builder()
                .title(post.getTitle())
                .secondTitle(post.getSecondTitle())
                .text(post.getText())
                .user(userService.getLoggedIn())
                .build();

        postRepository.saveAndFlush(postEntity);
    }

    public List<PostEntity> getAllPostsList(){
        return postRepository.findAll();
    }
}
