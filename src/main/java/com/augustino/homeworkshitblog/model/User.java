package com.augustino.homeworkshitblog.model;

import com.augustino.homeworkshitblog.entities.Role;
import com.augustino.homeworkshitblog.entities.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Long id;
    private String name;
    private String password;
    private List<Role> roles;


    public static User map(UserEntity userEntity){
        return User.builder()
                .id(userEntity.getId())
                .name(userEntity.getName())
                .password(userEntity.getPassword())
                .roles(userEntity.getRoles())
                .build();
    }
}
