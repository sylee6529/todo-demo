package com.example.todoproject.dto;

import com.example.todoproject.entity.Todo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TodoResponse {
    
    private Long id;
    private Long userId;
    private String title;
    private String content;
    private Boolean isDone;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    public static TodoResponse from(Todo todo) {
        return TodoResponse.builder()
                .id(todo.getId())
                .userId(todo.getUser().getId())
                .title(todo.getTitle())
                .content(todo.getContent())
                .isDone(todo.getIsDone())
                .createdAt(todo.getCreatedAt())
                .updatedAt(todo.getUpdatedAt())
                .build();
    }
}