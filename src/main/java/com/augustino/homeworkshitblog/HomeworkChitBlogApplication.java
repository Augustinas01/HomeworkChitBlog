package com.augustino.homeworkshitblog;

import com.augustino.homeworkshitblog.entities.*;
import com.augustino.homeworkshitblog.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Autowired
    CommentRepository commentRepository;

    @Value("${createfromscratch}")
    private Boolean createfromscratch;

    public static void main(String[] args) {
        SpringApplication.run(HomeworkChitBlogApplication.class, args);
    }

    @Override
    public void run(String... args) {


        if(createfromscratch){

            createAuthorities();
            createRoles();
            createUsers();
            createPosts();
            createComments();
        }



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
                                     authorityRepository.findByName("UPDATE")))
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
                .text("Lorem Ipsum bla bla bla")
                .likes(4L)
                .imageName("e4676912-d6c0-4b05-8f0f-2efad25cee22test3.jpg")
                .user(userRepository.getById(1L))
                .isDeleted(false)
                .build();


        PostEntity postEntity2 = PostEntity.builder()
                .text("Upsum Lorum")
                .likes(2L)
                .imageName("b9e31e6f-5ada-4a4b-9b7c-bbe1e94505f6test2.jpeg")
                .user(userRepository.getById(1L))
                .isDeleted(false)
                .build();

        postRepository.save(postEntity);
        postRepository.save(postEntity2);

        postRepository.flush();


    }


    private void createComments(){

        CommentEntity commentEntity = CommentEntity.builder()
                .user(userRepository.getById(1L))
                .post(postRepository.findAll().get(0))
                .text("test test test")
                .build();

        commentRepository.saveAndFlush(commentEntity);

    }

}
