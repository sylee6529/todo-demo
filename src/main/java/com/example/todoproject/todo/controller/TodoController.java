package com.example.todoproject.todo.controller;

import com.example.todoproject.dto.CommonResponse;
import com.example.todoproject.todo.dto.CreateTodoRequest;
import com.example.todoproject.todo.dto.TodoResponse;
import com.example.todoproject.todo.dto.UpdateTodoRequest;
import com.example.todoproject.todo.entity.Todo;
import com.example.todoproject.user.entity.User;
import com.example.todoproject.todo.service.TodoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/todos")
@RequiredArgsConstructor
@Validated
public class TodoController {

    private final TodoService todoService;

    @PostMapping
    public ResponseEntity<CommonResponse<TodoResponse>> createTodo(
            @Valid @RequestBody CreateTodoRequest request) {
        // TODO: 실제 구현시 인증된 사용자 정보를 가져와야 함
        User user = User.builder().id(request.userId()).build();
        Todo todo = todoService.createTodo(request, user);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CommonResponse.success("Todo가 성공적으로 생성되었습니다", TodoResponse.from(todo)));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<CommonResponse<List<TodoResponse>>> getTodosByUserId(
            @PathVariable @Positive(message = "사용자 ID는 양수여야 합니다") Long userId) {
        List<Todo> todos = todoService.getTodosByUserId(userId);
        List<TodoResponse> responses = todos.stream().map(TodoResponse::from).collect(Collectors.toList());
        return ResponseEntity.ok(CommonResponse.success(responses));
    }

    @GetMapping("/user/{userId}/status")
    public ResponseEntity<CommonResponse<List<TodoResponse>>> getTodosByUserIdAndStatus(
            @PathVariable @Positive(message = "사용자 ID는 양수여야 합니다") Long userId,
            @RequestParam @NotNull(message = "완료 상태는 필수입니다") Boolean isDone) {
        List<Todo> todos = todoService.getTodosByUserIdAndIsDone(userId, isDone);
        List<TodoResponse> responses = todos.stream().map(TodoResponse::from).collect(Collectors.toList());
        return ResponseEntity.ok(CommonResponse.success(responses));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse<TodoResponse>> getTodoById(
            @PathVariable @Positive(message = "Todo ID는 양수여야 합니다") Long id) {
        Todo todo = todoService.getTodoById(id);
        return ResponseEntity.ok(CommonResponse.success(TodoResponse.from(todo)));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CommonResponse<TodoResponse>> updateTodo(
            @PathVariable @Positive(message = "Todo ID는 양수여야 합니다") Long id,
            @Valid @RequestBody UpdateTodoRequest request) {
        Todo updatedTodo = todoService.updateTodo(id, request);
        return ResponseEntity.ok(CommonResponse.success("Todo가 성공적으로 수정되었습니다", TodoResponse.from(updatedTodo)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CommonResponse<Void>> deleteTodo(
            @PathVariable @Positive(message = "Todo ID는 양수여야 합니다") Long id) {
        todoService.deleteTodo(id);
        return ResponseEntity.ok(CommonResponse.success("Todo가 성공적으로 삭제되었습니다", null));
    }
}