package com.example.todoproject.todo.dto;

import com.example.todoproject.common.ValidationMessages;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record CreateTodoRequest(
    @NotNull(message = ValidationMessages.USER_ID_REQUIRED)
    @Positive(message = ValidationMessages.USER_ID_POSITIVE)
    Long userId,
    
    @NotBlank(message = ValidationMessages.TITLE_REQUIRED)
    @Size(max = 200, message = ValidationMessages.TITLE_SIZE_CREATE)
    String title,
    
    @Size(max = 5000, message = ValidationMessages.CONTENT_SIZE)
    String content
) {}