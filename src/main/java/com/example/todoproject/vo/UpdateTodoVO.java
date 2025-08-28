package com.example.todoproject.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateTodoVO {
    
    private final String title;
    private final String content;
    private final Boolean isDone;
}