package com.example.todolist.controller;


import com.example.todolist.domain.TodoDTO;
import com.example.todolist.domain.TodoUpdateDTO;
import com.example.todolist.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/todo")
@PreAuthorize("hasAuthority('User')")
public class TodoController {

    private final TodoService todoService;

    // todo 전체 조회인데 deleteYN false만
    @GetMapping("/all")
    public  ResponseEntity<List<TodoDTO>> getAllTodo(){
        List<TodoDTO> lst = todoService.getAllTodo();
        return new ResponseEntity<>(lst, HttpStatus.OK);
    }

    // todo 단일 조회
    @GetMapping("/{todoId}")
    public  ResponseEntity<TodoDTO> getTodo(@PathVariable("todoId") String todoId){
        TodoDTO rtn = todoService.getTodoById((Long.parseLong(todoId)));
        return new ResponseEntity<>(rtn, HttpStatus.OK);
    }

    // todo 추가
    @PostMapping("/add")
    public ResponseEntity<TodoDTO> addTodo(@RequestBody TodoDTO todoDTO){
        todoService.createTodo(todoDTO);
        return ResponseEntity.ok(todoDTO);
    }

    // todo 수정 ??
    @PostMapping("/update/{todoId}")
    public ResponseEntity<TodoDTO> updateTodo(@PathVariable("todoId") String todoId, @RequestBody TodoUpdateDTO updateDTO){
        TodoDTO rtn = todoService.updateTodo(Long.parseLong(todoId), updateDTO);
        return ResponseEntity.ok(rtn);
    }

    // todo 삭제 deleteYN -> true로
    @PostMapping("/delete/{todoId}")
    public ResponseEntity<TodoDTO> deleteTodo(@PathVariable("todoId") String todoId){
        todoService.deleteTodo(Long.parseLong(todoId));
        return ResponseEntity.ok(null);
    }

    // todo 완료 doneYN -> true로
    @PostMapping("/done/{todoId}")
    public ResponseEntity<TodoDTO> doneTodo(@PathVariable("todoId") String todoId){
        TodoDTO todoDTO = todoService.getTodoById(Long.parseLong(todoId));
        TodoUpdateDTO updateDTO = new TodoUpdateDTO(todoDTO.getTitle(), todoDTO.getDeadline(), todoDTO.getUpdatedAt(), todoDTO.getDeleteYn(), true);
        todoService.updateTodo(Long.parseLong(todoId), updateDTO);
        return ResponseEntity.ok(null);
    }

    // todo 검색 (제목으로)
    @GetMapping("/find/{todoTitle}")
    public  ResponseEntity<List<TodoDTO>> findTodo(@PathVariable("todoTitle") String todoTitle){
        List<TodoDTO> rtn = todoService.findAllByTitle(todoTitle);
        return new ResponseEntity<>(rtn, HttpStatus.OK);
    }
}
