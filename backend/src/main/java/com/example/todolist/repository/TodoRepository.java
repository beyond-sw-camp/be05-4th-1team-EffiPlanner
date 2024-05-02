package com.example.todolist.repository;

import com.example.todolist.domain.Todo;
import com.example.todolist.domain.TodoDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findAllByTitleLike(String title);
}
