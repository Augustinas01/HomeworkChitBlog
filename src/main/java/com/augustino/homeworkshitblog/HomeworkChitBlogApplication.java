package com.augustino.homeworkshitblog;

import com.augustino.homeworkshitblog.entities.Authority;
import com.augustino.homeworkshitblog.entities.PostEntity;
import com.augustino.homeworkshitblog.entities.Role;
import com.augustino.homeworkshitblog.entities.UserEntity;
import com.augustino.homeworkshitblog.repository.AuthorityRepository;
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

    @Autowired
    AuthorityRepository authorityRepository;

    public static void main(String[] args) {
        SpringApplication.run(HomeworkChitBlogApplication.class, args);
    }

    @Override
    public void run(String... args) {

        createAuthorities();

        createRoles();

        createUsers();

        createPosts();


//        System.out.println(accountRepository.findByName("mainAcc"));
    }


    private void createAuthorities(){
        Authority create = Authority.builder().name("CREATE").build();
        Authority read = Authority.builder().name("READ").build();
        Authority update = Authority.builder().name("UPDATE").build();
        Authority delete = Authority.builder().name("DELETE").build();
        Authority comment = Authority.builder().name("COMMENT").build();



        authorityRepository.save(create);
        authorityRepository.save(read);
        authorityRepository.save(update);
        authorityRepository.save(delete);
        authorityRepository.save(comment);
        authorityRepository.flush();
    }

    private void createRoles(){

        Role adminRole = Role.builder()
                .name("ROLE_ADMIN")
                .authorities(authorityRepository.findAll())
                .build();

        Role userRole = Role.builder()
                .name("ROLE_USER")
                .authorities(List.of(authorityRepository.findByName("COMMENT"),
                                     authorityRepository.findByName("UPDATE"),
                                     authorityRepository.findByName("DELETE")))
                .build();

        roleRepository.save(adminRole);
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
                .user(userRepository.getById(1L))
                .build();


        PostEntity postEntity2 = PostEntity.builder()
                .title("testinis2")
                .secondTitle("TIKRAI TESTINIS2")
                .text("Lorem Ipsum bla bla bla2")
                .likes(0L)
                .user(userRepository.getById(1L))
                .build();

        postRepository.save(postEntity);
        postRepository.save(postEntity2);

        postRepository.flush();


    }


}
