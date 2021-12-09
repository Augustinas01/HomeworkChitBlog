package com.augustino.homeworkshitblog.repository;

import com.augustino.homeworkshitblog.entities.PostEntity;
import com.augustino.homeworkshitblog.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PostRepository extends JpaRepository<PostEntity, UUID> {

    List<PostEntity> findAllByIsDeletedEquals(Boolean b);
}
