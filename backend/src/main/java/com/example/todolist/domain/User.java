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
	@Column(name = "user_id")
	private String userId;

	private String userName;
	private String email;
	private String password;
	private Boolean deleteYn;

	@Builder
	public User(String userId, String userName, String email, String password, Boolean deleteYn) {
		this.userId = userId;
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.deleteYn = deleteYn;
	}
}
