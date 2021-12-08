package com.augustino.homeworkshitblog.service;


import com.augustino.homeworkshitblog.entities.UserEntity;
import com.augustino.homeworkshitblog.repository.RoleRepository;
import com.augustino.homeworkshitblog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository accountRepository;

    private final RoleRepository roleRepository;

    private final UserDetailsService userDetailsService;


    public UserEntity getUserByName(String username){
         if(accountRepository.findByName(username).isPresent()) {
             return accountRepository.findByName(username).get();
         }else{
             throw new UsernameNotFoundException(username);
         }
    }

    public UserEntity getLoggedIn(){
        return getUserByName(userDetailsService.getCurrentlyLoggedIn().getUsername());
    }


    public void createUser(UserEntity user){

        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        user.setPassword(
                encoder.encode(
                        user.getPassword()
                )
        );
        user.setRoles(List.of(roleRepository.findByName("ROLE_USER")));

//        String name = user.getName();
//        String password = user.getPassword();

//        UserEntity user = UserEntity.builder()
//                .name(name)
//                .password(encoder.encode(password))
//                .roles(List.of(roleRepository.findByName("ROLE_USER")))
//                .build();
        accountRepository.saveAndFlush(user);
    }



}
