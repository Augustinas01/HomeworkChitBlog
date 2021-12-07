package com.augustino.homeworkshitblog.service;

import com.augustino.homeworkshitblog.entities.UserEntity;
import com.augustino.homeworkshitblog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<UserEntity> user = userRepository.findByName(username);

        return user
                .map(this::mapUser)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    public UserDetails getCurrentlyLoggedIn(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return (UserDetails) principal;
//        String username;
//        if (principal instanceof UserDetails) {
//            username = ((UserDetails)principal).getUsername();
//        } else {
//            username = principal.toString();
//        }
    }

    private UserDetails mapUser(UserEntity u) {
        return new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
//                return u.getRoles().stream()
//                        .map(r -> new SimpleGrantedAuthority("ROLE_" + r))
//                        .collect(Collectors.toList());

//                return List.of( new SimpleGrantedAuthority("ROLE_ADMIN"));

                System.out.println("SUVEIKEEEEEEE");
                return u.getRoles();
            }

            @Override
            public String getPassword() {
                return u.getPassword();
            }

            @Override
            public String getUsername() {
                return u.getName();
            }

            @Override
            public boolean isAccountNonExpired() {
                return true;
            }

            @Override
            public boolean isAccountNonLocked() {
                return true;
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return true;
            }

            @Override
            public boolean isEnabled() {
                return true;
            }
        };
    }



}

