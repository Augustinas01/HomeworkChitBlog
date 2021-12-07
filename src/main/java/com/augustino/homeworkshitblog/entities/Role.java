package com.augustino.homeworkshitblog.entities;


import lombok.*;
import org.springframework.security.core.GrantedAuthority;


import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

//    @ManyToMany(mappedBy = "roles")
//    private Collection<UserEntity> users;


    @Override
    public String getAuthority() {
        return "ROLE_ADMIN";
    }
}
