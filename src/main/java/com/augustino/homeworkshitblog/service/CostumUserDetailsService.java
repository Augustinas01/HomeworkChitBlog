package com.augustino.homeworkshitblog.service;

import com.augustino.homeworkshitblog.config.UserPrincipal;
import com.augustino.homeworkshitblog.model.User;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CostumUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userService.getUserByName(username);


        return UserPrincipal.builder()
                .id(user.getId())
                .username(user.getName())
                .password(user.getPassword())
                .roles(user.getRoles())
                .build();


    }




}

