package com.example.todolist.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "users")
public class User {
    
    @Id
	@Column(name = "Email")
	private String email;

	private String userName;
	private String userNickname;
	private String password;
	private Boolean deleteYn;

	@Builder
	public User(String email, String userName, String userNickname, String password, Boolean deleteYn) {
		this.email = email;
		this.userName = userName;
		this.userNickname = userNickname;
		this.password = password;
		this.deleteYn = deleteYn;
	}
}
