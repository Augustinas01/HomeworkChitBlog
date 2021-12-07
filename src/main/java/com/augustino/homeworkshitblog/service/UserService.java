package com.augustino.homeworkshitblog.service;


import com.augustino.homeworkshitblog.entities.UserEntity;
import com.augustino.homeworkshitblog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository accountRepository;
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



}
