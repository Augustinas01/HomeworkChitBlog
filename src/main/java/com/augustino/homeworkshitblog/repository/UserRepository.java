package com.augustino.homeworkshitblog.repository;

import com.augustino.homeworkshitblog.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, String> {

    Optional<UserEntity> findById(Long id);
    Optional<UserEntity> findByName(String username);
}
