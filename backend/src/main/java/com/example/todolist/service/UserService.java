package com.example.todolist.service;

import java.util.ArrayList;
import java.util.List;

//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.todolist.domain.User;
import com.example.todolist.domain.UserSignUpDTO;
import com.example.todolist.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    //private final PasswordEncoder passwordEncoder;
    

    @Transactional
	public List<String> signUp(UserSignUpDTO userSignUpDTO) {

		String email = userSignUpDTO.getEmail();

		List<String> msg = new ArrayList<>();
		if (userRepository.existsByEmail(email)) {
			msg.add("이미 가입되어 있는 이메일 입니다.");
		}
		if (!msg.isEmpty()) {
			return msg;
		}

		User user = User.builder()
                .email(userSignUpDTO.getEmail())
                .userName(userSignUpDTO.getUsername())
                .password(userSignUpDTO.getPassword1()) // 비밀번호 암호화 필요
                .userNickname(userSignUpDTO.getUserNickname())
                .deleteYn(false)
                .build();
        this.userRepository.save(user);
        msg.add(user.getEmail() + "님 가입을 환영합니다.");
		return msg;
	}
}
