package com.example.todolist.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserSignUpDTO {
	private String email;
	private String userNickname;
	private String username;
	private String password1;
	private String password2;
}
