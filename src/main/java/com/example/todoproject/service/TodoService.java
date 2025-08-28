package com.example.todoproject.service;

import com.example.todoproject.dto.CreateTodoRequest;
import com.example.todoproject.dto.UpdateTodoRequest;
import com.example.todoproject.entity.Todo;
import com.example.todoproject.entity.User;
import com.example.todoproject.repository.TodoRepository;
import com.example.todoproject.vo.CreateTodoVO;
import com.example.todoproject.vo.UpdateTodoVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TodoService {

    private final TodoRepository todoRepository;

    public Todo createTodo(CreateTodoRequest request, User user) {
        CreateTodoVO vo = new CreateTodoVO(user, request.getTitle(), request.getContent());
        Todo todo = Todo.builder()
                .user(vo.getUser())
                .title(vo.getTitle())
                .content(vo.getContent())
                .build();
        
        return todoRepository.save(todo);
    }

    @Transactional(readOnly = true)
    public List<Todo> getTodosByUserId(Long userId) {
        return todoRepository.findByUserId(userId);
    }

    @Transactional(readOnly = true)
    public List<Todo> getTodosByUserIdAndIsDone(Long userId, Boolean isDone) {
        return todoRepository.findByUserIdAndIsDone(userId, isDone);
    }

    @Transactional(readOnly = true)
    public Todo getTodoById(Long id) {
        return todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo not found with id: " + id));
    }

    public Todo updateTodo(Long id, UpdateTodoRequest request) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo not found with id: " + id));
        
        UpdateTodoVO vo = new UpdateTodoVO(request.getTitle(), request.getContent(), request.getIsDone());
        todo.update(vo);
        return todo;
    }

    public void deleteTodo(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo not found with id: " + id));
        todoRepository.delete(todo);
    }
}