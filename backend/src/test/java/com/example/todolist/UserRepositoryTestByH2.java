package com.example.todolist;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.todolist.domain.User;
import com.example.todolist.repository.UserRepository;

@DataJpaTest
public class UserRepositoryTestByH2 {
    
    @Autowired
    private UserRepository userRepository;

    @Test
    void saveUser() {
        // given
        User user = User.builder()
                .email("user001@naver.com")
                .userName("user001")
                .userNickname("user1")
                .password("1234")
                .deleteYn(false)
                .build();
        // when
        User saveUser = userRepository.save(user);

        // then
        assertEquals(user.getEmail(), saveUser.getEmail());
        assertEquals(user.getUserName(), saveUser.getUserName());
        assertEquals(user.getUserNickname(), saveUser.getUserNickname());
    }

    @Test
    void findByEmail() {
        // given
        User user = User.builder()
                .email("user001@naver.com")
                .userName("user001")
                .userNickname("user1")
                .password("1234")
                .deleteYn(false)
                .build();
        userRepository.save(user);

        // when
        Optional<User> findUser = userRepository.findByEmail("user001@naver.com");

        // then
        assertEquals(user.getEmail(), findUser.get().getEmail());
        assertEquals(user.getUserName(), findUser.get().getUserName());
        assertEquals(user.getUserNickname(), findUser.get().getUserNickname());

    }

    @Test
    void login() {
        // given
        User user = User.builder()
                .email("user001@naver.com")
                .userName("user001")
                .userNickname("user1")
                .password("1234")
                .deleteYn(false)
                .build();
        userRepository.save(user);

        // when
        Optional<User> findUser = userRepository.findByEmail("user001@naver.com");
        String givenPassword = "1234";
        String givenEmail = "user001@naver.com";

        // then
        assertEquals(findUser.get().getEmail(), givenEmail);
        assertEquals(findUser.get().getPassword(), givenPassword);
    }
}
