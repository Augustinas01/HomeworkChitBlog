package com.augustino.homeworkshitblog;

import com.augustino.homeworkshitblog.entities.AccountEntity;
import com.augustino.homeworkshitblog.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HomeworkChitBlogApplication implements CommandLineRunner {

    @Autowired
    AccountRepository accountRepository;

    public static void main(String[] args) {
        SpringApplication.run(HomeworkChitBlogApplication.class, args);
    }

    @Override
    public void run(String... args) {

        AccountEntity mainAcc = AccountEntity.builder().name("mainAcc").build();
        accountRepository.saveAndFlush(mainAcc);

        System.out.println(accountRepository.findById(1L));
    }

}
