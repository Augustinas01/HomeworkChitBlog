package com.augustino.homeworkshitblog.model;

import com.augustino.homeworkshitblog.entities.CommentEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    private String text;
    private Long userID;
    private UUID commentID;
    private UUID postID;


    public static Comment map(CommentEntity commentEntity){
        return Comment.builder()
                .text(commentEntity.getText())
                .commentID(commentEntity.getId())
                .userID(commentEntity.getUser().getId())
                .build();
    }
}
