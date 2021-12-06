package com.augustino.homeworkshitblog.repository;

import com.augustino.homeworkshitblog.entities.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<AccountEntity, String> {
}
