package com.example.todolist.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.todolist.domain.SignInResponse;
import com.example.todolist.domain.User;
import com.example.todolist.domain.UserSignInDTO;
import com.example.todolist.domain.UserSignUpDTO;
import com.example.todolist.repository.UserRepository;
import com.example.todolist.security.TokenProvider;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
	private final TokenProvider tokenProvider;
    

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
                .password(passwordEncoder.encode(userSignUpDTO.getPassword1()))
                .userNickname(userSignUpDTO.getUserNickname())
                .deleteYn(false)
                .build();
        this.userRepository.save(user);
        msg.add(user.getEmail() + "님 가입을 환영합니다.");
		return msg;
	}

	@Transactional
	public SignInResponse signIn(UserSignInDTO userSignInDTO){
		Optional<User> user = userRepository.findByEmail(userSignInDTO.getEmail());
		if (user.isPresent() && !user.get().getDeleteYn()) {
			if (passwordEncoder.matches(userSignInDTO.getPassword(), user.get().getPassword())) {
				String token = tokenProvider.createToken(String.format("%s:%s", user.get().getEmail(), "User"));
				return new SignInResponse(user.get().getEmail(), "User", token, user.get().getUserNickname(), "로그인 성공");
			}
			return new SignInResponse(null, null, null, null, "비밀번호가 일치하지 않습니다");
		}
		return new SignInResponse(null, null, null, null, "존재하지 않는 회원입니다");
	}

	public List<String> emailCheck(String email) {
		List<String> msg = new ArrayList<>();
		if (userRepository.existsByEmail(email)) {
			msg.add("이미 가입되어 있는 이메일 입니다.");
		} else {
			msg.add("사용 가능한 이메일 입니다.");
		}
		return msg;
	}

	public String signOut(UserSignInDTO userSignInDTO) {
		Optional<User> user = userRepository.findByEmail(userSignInDTO.getEmail());
		if (user.isPresent()) {
			if (passwordEncoder.matches(userSignInDTO.getPassword(), user.get().getPassword())) {
				User updateUser = User.builder()
						.email(user.get().getEmail())
						.userName(user.get().getUserName())
						.userNickname(user.get().getUserNickname())
						.password(user.get().getPassword())
						.deleteYn(true)
						.build();
				userRepository.save(updateUser);
				return "탈퇴 성공";
			}
			return "비밀번호가 일치하지 않습니다";
		}
		return "존재하지 않는 이메일입니다";
	}

	public User userInfo(String email) {
		Optional<User> user = userRepository.findByEmail(email);
		User returnUser = User.builder()
								.email(user.get().getEmail())
								.userName(user.get().getUserName())
								.userNickname(user.get().getUserNickname())
								.deleteYn(user.get().getDeleteYn())
								.build();
		return returnUser;
	}

}
