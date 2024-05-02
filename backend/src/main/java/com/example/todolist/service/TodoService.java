package com.example.todolist.service;

import com.example.todolist.domain.*;
import com.example.todolist.repository.CategoryRepository;
import com.example.todolist.repository.TodoRepository;
import com.example.todolist.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TodoService {

    private final TodoRepository todoRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;


    //전체 조회
    public List<TodoDTO> getAllTodo(){
        List<Todo> list = todoRepository.findAll();
        return list.stream().map(TodoDTO::new).collect(Collectors.toList());
    }

    // id로 조회
    public TodoDTO getTodoById(Long id){
        Todo rtn = todoRepository.findById(id).orElseThrow();
        return new TodoDTO(rtn);
    }

    // todo 작성
    public void createTodo(TodoDTO params) {
        String userId = params.getUserId();
        Long categoryId = params.getCategoryId();

        User user = userRepository.findById(userId).orElseThrow();
        Category category = categoryRepository.findById(categoryId).orElseThrow();

        Todo todo = params.toEntity(user, category);
        todoRepository.save(todo);
    }

    // todo 수정
    public TodoDTO updateTodo(Long id, TodoUpdateDTO params){
        Todo todo = todoRepository.findById(id).orElseThrow();
        todo.update(params.getTitle(), params.getDeadline(), params.getDeleteYn(), params.getDoneYn());
        return new TodoDTO(todo);
    }

    //todo 삭제
    public void deleteTodo(Long id){
        Todo todo = todoRepository.findById(id).orElseThrow();
        todo.delete();
    }

    //todo 검색 (제목으로)
    public List<TodoDTO> findAllByTitle(String title){
        List<Todo> list = todoRepository.findAllByTitleLike(title);
        return list.stream().map(TodoDTO::new).collect(Collectors.toList());
    }
}
