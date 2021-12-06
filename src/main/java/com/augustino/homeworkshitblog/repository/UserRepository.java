package com.augustino.homeworkshitblog.repository;




import com.augustino.homeworkshitblog.model.User;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public class UserRepository {

    private List<User> users = new ArrayList<>();


    @PostConstruct

    public void init() {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        users.add(User.builder()
                .username("jim")
                .password(encoder.encode("jimp"))
                .roles(Set.of("TEST"))
                .build());

        users.add(User.builder()
                .username("admin")
                .password(encoder.encode("adminp"))
                .roles(Set.of("ADMIN", "TEST"))
                .build());

        users.add(User.builder()
                .username("user")
                .password(encoder.encode("userp"))
                .build());

//        log.info("Dummy {} users added ", users.size());
    }

    public User findByUsernamePass(String username, String password) {
        return users.stream()
                .filter(u -> u.getUsername().equals(username) && u.getPassword().equals(password)).findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public Optional<User> findByUsername(String username) {
        return users.stream()
                .filter(u -> u.getUsername().equals(username)).findFirst();
    }
}
