package com.augustino.homeworkshitblog.repository;

import com.augustino.homeworkshitblog.entities.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorityRepository extends JpaRepository<Authority,Long> {

    Authority findByName(String name);

}
