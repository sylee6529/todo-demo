package com.example.todoproject.controller;

import com.example.todoproject.dto.CreateTodoRequest;
import com.example.todoproject.dto.TodoResponse;
import com.example.todoproject.dto.UpdateTodoRequest;
import com.example.todoproject.entity.Todo;
import com.example.todoproject.entity.User;
import com.example.todoproject.service.TodoService;
import com.example.todoproject.vo.CreateTodoVO;
import com.example.todoproject.vo.UpdateTodoVO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/todos")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @PostMapping
    public ResponseEntity<TodoResponse> createTodo(
            @Valid @RequestBody CreateTodoRequest request) {
        // TODO: 실제 구현시 인증된 사용자 정보를 가져와야 함
        User user = User.builder().id(request.getUserId()).build();
        Todo todo = todoService.createTodo(request, user);
        return ResponseEntity.status(HttpStatus.CREATED).body(TodoResponse.from(todo));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<TodoResponse>> getTodosByUserId(
            @PathVariable Long userId) {
        List<Todo> todos = todoService.getTodosByUserId(userId);
        List<TodoResponse> responses = todos.stream().map(TodoResponse::from).collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/user/{userId}/status")
    public ResponseEntity<List<TodoResponse>> getTodosByUserIdAndStatus(
            @PathVariable Long userId,
            @RequestParam Boolean isDone) {
        List<Todo> todos = todoService.getTodosByUserIdAndIsDone(userId, isDone);
        List<TodoResponse> responses = todos.stream().map(TodoResponse::from).collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoResponse> getTodoById(@PathVariable Long id) {
        Todo todo = todoService.getTodoById(id);
        return ResponseEntity.ok(TodoResponse.from(todo));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TodoResponse> updateTodo(
            @PathVariable Long id,
            @RequestBody UpdateTodoRequest request) {
        Todo updatedTodo = todoService.updateTodo(id, request);
        return ResponseEntity.ok(TodoResponse.from(updatedTodo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
        todoService.deleteTodo(id);
        return ResponseEntity.noContent().build();
    }
}