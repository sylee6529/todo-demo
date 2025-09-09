package com.example.todoproject.todo.service;

import com.example.todoproject.todo.dto.CreateTodoRequest;
import com.example.todoproject.todo.dto.UpdateTodoRequest;
import com.example.todoproject.todo.entity.Todo;
import com.example.todoproject.user.entity.User;
import com.example.todoproject.exception.TodoException;
import com.example.todoproject.exception.TodoErrorCode;
import com.example.todoproject.exception.UserErrorCode;
import com.example.todoproject.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TodoService {

    private final TodoRepository todoRepository;

    public Todo createTodo(CreateTodoRequest request, User user) {
        Todo todo = Todo.builder()
                .user(user)
                .title(request.title())
                .content(request.content())
                .build();
        
        return todoRepository.save(todo);
    }

    @Transactional(readOnly = true)
    public List<Todo> getTodosByUserId(Long userId) {
        validateUserId(userId);
        return todoRepository.findByUserId(userId);
    }

    @Transactional(readOnly = true)
    public List<Todo> getTodosByUserIdAndIsDone(Long userId, Boolean isDone) {
        validateUserId(userId);
        return todoRepository.findByUserIdAndIsDone(userId, isDone);
    }

    @Transactional(readOnly = true)
    public Todo getTodoById(Long id) {
        return todoRepository.findById(id)
                .orElseThrow(() -> new TodoException(TodoErrorCode.TODO_NOT_FOUND));
    }

    public Todo updateTodo(Long id, UpdateTodoRequest request) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new TodoException(TodoErrorCode.TODO_NOT_FOUND));
        
        validateUpdateRequest(request);
        
        todo.update(request);
        return todo;
    }

    public void deleteTodo(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new TodoException(TodoErrorCode.TODO_NOT_FOUND));
        todoRepository.delete(todo);
    }
    
    private void validateUserId(Long userId) {
        if (userId == null || userId <= 0) {
            throw new TodoException(UserErrorCode.USER_ID_INVALID);
        }
    }
    
    private void validateUpdateRequest(UpdateTodoRequest request) {
        if (request.title() == null && request.content() == null && request.isDone() == null) {
            throw new TodoException(TodoErrorCode.TODO_UPDATE_EMPTY);
        }
    }
}