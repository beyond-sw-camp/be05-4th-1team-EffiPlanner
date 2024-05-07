package com.example.todolist.controller;

import java.util.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.todolist.domain.SignInResponse;
import com.example.todolist.domain.UserSignInDTO;
import com.example.todolist.domain.UserSignUpDTO;
import com.example.todolist.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class UserController {
	private final UserService userService;

    @PostMapping("/signup")
	public ResponseEntity<List<String>> signUp(@RequestBody UserSignUpDTO userSignUpDTO) {
		List<String> msg = new ArrayList<>();
		if (!userSignUpDTO.getPassword1().equals(userSignUpDTO.getPassword2())) {
			msg.add("2개의 패스워드가 일치하지 않습니다.");
			return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
		}
		msg = userService.signUp(userSignUpDTO);
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}

	@GetMapping("/emailcheck")
	public ResponseEntity<List<String>> emailCheck(@RequestParam String email) {
		List<String> msg = userService.emailCheck(email);
		if(msg.get(0).equals("이미 가입되어 있는 이메일 입니다.")){
			return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}

	@PostMapping("/signin")
	public ResponseEntity<SignInResponse> signIn(@RequestBody UserSignInDTO userSignInDTO) {
		SignInResponse msg = userService.signIn(userSignInDTO);
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}

	@DeleteMapping("/signout")
	public ResponseEntity<String> signOut(@RequestBody UserSignInDTO userSignInDTO) {
		String msg = userService.signOut(userSignInDTO);
		if (msg.equals("탈퇴 성공")){
			return new ResponseEntity<>(msg, HttpStatus.OK);
		}
		return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
	}
}
