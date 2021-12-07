package com.augustino.homeworkshitblog;

import com.augustino.homeworkshitblog.entities.PostEntity;
import com.augustino.homeworkshitblog.entities.Role;
import com.augustino.homeworkshitblog.entities.UserEntity;
import com.augustino.homeworkshitblog.repository.PostRepository;
import com.augustino.homeworkshitblog.repository.UserRepository;
import com.augustino.homeworkshitblog.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Set;

@SpringBootApplication
public class HomeworkChitBlogApplication implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PostRepository postRepository;

    public static void main(String[] args) {
        SpringApplication.run(HomeworkChitBlogApplication.class, args);
    }

    @Override
    public void run(String... args) {

        createRoles();

        createUsers();

        createPosts();


//        System.out.println(accountRepository.findByName("mainAcc"));
    }



    private void createRoles(){

        Role mainRole = Role.builder().name("ROLE_ADMIN").build();
        Role userRole = Role.builder().name("ROLE_USER").build();

        roleRepository.save(mainRole);
        roleRepository.save(userRole);
        roleRepository.flush();

    }

    private void createUsers(){

        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        UserEntity mainAcc = UserEntity.builder()
                .name("mainAcc")
                .password(encoder.encode("test"))
                .roles(List.of(roleRepository.findByName("ROLE_ADMIN")))
                .build();
        userRepository.saveAndFlush(mainAcc);

    }

    private void createPosts(){

        PostEntity postEntity = PostEntity.builder()
                .title("testinis")
                .secondTitle("TIKRAI TESTINIS")
                .text("Lorem Ipsum bla bla bla")
                .likes(0L)
                .user(userRepository.findById(1L).get())
                .build();
        postRepository.save(postEntity);

        PostEntity postEntity2 = PostEntity.builder()
                .title("testinis2")
                .secondTitle("TIKRAI TESTINIS2")
                .text("Lorem Ipsum bla bla bla2")
                .likes(0L)
                .user(userRepository.findById(1L).get())
                .build();

        postRepository.save(postEntity2);
        postRepository.flush();


    }


}
