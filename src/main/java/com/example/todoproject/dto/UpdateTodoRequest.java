package com.example.todoproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTodoRequest {
    
    private String title;
    private String content;
    private Boolean isDone;
}