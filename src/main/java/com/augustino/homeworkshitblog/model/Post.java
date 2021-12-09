package com.augustino.homeworkshitblog.model;

import com.augustino.homeworkshitblog.entities.CommentEntity;
import com.augustino.homeworkshitblog.entities.PostEntity;
import com.augustino.homeworkshitblog.entities.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post {


    private UUID id;
    private Date createdAt;
    private User user;
    private Long likes;
    private String text;
    private String imageFileName;
    private List<Comment> commentList;
    private Boolean isDeleted;

    private MultipartFile image;
    private Long userID;


    public static Post mapPost(PostEntity postEntity, UserEntity userEntity, List<CommentEntity> commentEntitiesByPostId){

        return Post.builder()
                .id(postEntity.getId())
                .createdAt(postEntity.getCreatedAt())
                .likes(postEntity.getLikes())
                .text(postEntity.getText())
                .imageFileName(postEntity.getImageName())
                .isDeleted(postEntity.getIsDeleted())
                .user(User.map(userEntity))
                .commentList(commentEntitiesByPostId.stream()
                        .map(Comment::map)
                        .toList())
                .build();
    }


    public static void setUser(User user){


    }


}
