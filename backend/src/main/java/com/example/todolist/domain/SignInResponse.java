package com.example.todolist.domain;

public record SignInResponse(String email, String type, String token, String userNickname, String message) {
}
