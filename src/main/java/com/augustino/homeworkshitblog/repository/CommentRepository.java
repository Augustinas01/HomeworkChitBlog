package com.augustino.homeworkshitblog.repository;

import com.augustino.homeworkshitblog.entities.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CommentRepository extends JpaRepository<CommentEntity, UUID> {

    List<CommentEntity> getCommentEntitiesByPostId(UUID postID);
}
