package com.augustino.homeworkshitblog.service;

import com.augustino.homeworkshitblog.entities.CommentEntity;
import com.augustino.homeworkshitblog.entities.PostEntity;
import com.augustino.homeworkshitblog.model.Comment;
import com.augustino.homeworkshitblog.model.Post;
import com.augustino.homeworkshitblog.model.User;
import com.augustino.homeworkshitblog.repository.CommentRepository;
import com.augustino.homeworkshitblog.repository.PostRepository;
import com.augustino.homeworkshitblog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final ServletContext ctx;

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;




    public void createPost(Post post, String... postImagePath){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        UUID imageID = UUID.randomUUID();
        post.setImageFileName(imageID + post.getImage().getOriginalFilename());

        String path = postImagePath.length == 0 ? "" : postImagePath[0];
        savePostImage(post, path);

        PostEntity postEntity = PostEntity.builder()
                .text(post.getText())
                .imageName(post.getImageFileName())
                .isDeleted(false)
                .likes(0L)
                .user(userRepository.findByName(auth.getName()).get())
                .build();

        postRepository.saveAndFlush(postEntity);
    }


    private void savePostImage(Post post, String postImagePath){

        String tempPath = ctx.getRealPath("/uploads/");

        if(!new File(tempPath).exists())
        {
            new File(tempPath).mkdir();
        }

        try{
            if(!postImagePath.isEmpty()) {

                if(!new File(postImagePath).exists())
                {
                    new File(postImagePath).mkdir();
                }

                File image = new File(postImagePath + post.getImageFileName());
                post.getImage().transferTo(image);

                Files.copy(image.toPath(),
                        Path.of(tempPath + post.getImageFileName()),
                        StandardCopyOption.REPLACE_EXISTING);
            }else{
                post.getImage().transferTo(new File(tempPath + post.getImageFileName()));
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public List<Post> getAllPostsList(){
        return postRepository.findAllByIsDeletedEquals(false).stream()
                .map(postEntity -> Post.mapPost(
                        postEntity,
                        userRepository.getById(postEntity.getUser().getId()),
                        commentRepository.getCommentEntitiesByPostId(postEntity.getId())
                        )
                )
                .toList();
    }


    public void commentPost(Comment comment){

        CommentEntity commentEntity = CommentEntity.builder()
                .post(postRepository.getById(comment.getPostID()))
                .text(comment.getText())
                .user(userRepository.getById(comment.getUserID()))
                .build();

        commentRepository.saveAndFlush(commentEntity);
    }

    public void likePost(UUID postID){
        PostEntity postEntity = postRepository.getById(postID);
        postEntity.setLikes(postEntity.getLikes() + 1);
        postRepository.saveAndFlush(postEntity);
    }

    public void deletePost(UUID postID){
        PostEntity postEntity = postRepository.getById(postID);
        postEntity.setIsDeleted(true);
        postRepository.saveAndFlush(postEntity);
    }

    public void editPost(UUID postID, String text){
        PostEntity postEntity = postRepository.getById(postID);
        postEntity.setText(text);
        postRepository.saveAndFlush(postEntity);
    }


//    public List<CommentEntity> getAllPostCommentListByPostID(UUID postID) {
//        commentRepository.getCommentEntitiesByPostId(postID);
//    }
}
