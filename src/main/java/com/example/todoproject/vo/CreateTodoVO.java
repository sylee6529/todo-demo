package com.example.todoproject.vo;

import com.example.todoproject.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateTodoVO {
    
    private final User user;
    private final String title;
    private final String content;
}