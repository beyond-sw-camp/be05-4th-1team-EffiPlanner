package com.example.todolist.service;

import com.example.todolist.domain.*;
import com.example.todolist.repository.CategoryRepository;
import com.example.todolist.repository.TodoRepository;
import com.example.todolist.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class TodoService {

    private final TodoRepository todoRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;


    //전체 조회
    // deleteYN이 false만 출력 -> 추가 필요
    public List<TodoDTO> getAllTodo(){
        List<Todo> list = todoRepository.findAll();
        List<Todo> result = new ArrayList<>();
        for (Todo res : list) {
            if (res.getDeleteYn() == false)
                result.add(res);
        }
        return result.stream().map(TodoDTO::new).collect(Collectors.toList());
    }

    // id로 조회
    public TodoDTO getTodoById(Long id){
        Todo rtn = todoRepository.findById(id).orElseThrow();
        if (rtn.getDeleteYn() == true)
            return null;
        return new TodoDTO(rtn);
    }

    // todo 작성
    public void createTodo(TodoDTO params) {
        String userEmail = params.getEmail();
        Long categoryId = params.getCategoryId();

        System.out.println(userEmail + categoryId);
        if (userEmail == null || categoryId == null) {
            throw new IllegalArgumentException("User email or category ID is null");
        }

        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new IllegalArgumentException("User not found with email: " + userEmail));
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("Category not found with ID: " + categoryId));

        Todo todo = params.toEntity(user, category);
        todoRepository.save(todo);
    }

    // todo 수정
    public TodoDTO updateTodo(Long id, TodoUpdateDTO params){
        Todo todo = todoRepository.findById(id).orElseThrow();
        todo.update(params.getTitle(), params.getDeadline(), params.getDeleteYn(), params.getDoneYn());
        todoRepository.save(todo);
        return new TodoDTO(todo);
    }

    //todo 삭제
    public void deleteTodo(Long id){
        Todo todo = todoRepository.findById(id).orElseThrow();
        todo.delete();
        todoRepository.save(todo);
    }

    //todo 검색 (제목으로)
    public List<TodoDTO> findAllByTitle(String title){
        List<Todo> list = todoRepository.findAllByTitleLike('%' + title + '%');
        List<Todo> result = new ArrayList<>();
        for (Todo res : list) {
            if (res.getDeleteYn() == false)
                result.add(res);
        }
        return result.stream().map(TodoDTO::new).collect(Collectors.toList());
    }
}
