package com.example.todolist.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserSignUpDTO {
	private String userId;
	private String username;
    private String email;
	private String password1;
	private String password2;
}
