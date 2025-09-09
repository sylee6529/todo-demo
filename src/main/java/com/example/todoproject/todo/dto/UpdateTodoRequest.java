package com.example.todoproject.todo.dto;

import com.example.todoproject.common.ValidationMessages;
import jakarta.validation.constraints.Size;

public record UpdateTodoRequest(
    @Size(min = 1, max = 200, message = ValidationMessages.TITLE_SIZE_UPDATE)
    String title,
    
    @Size(max = 5000, message = ValidationMessages.CONTENT_SIZE)
    String content,
    
    Boolean isDone
) {}