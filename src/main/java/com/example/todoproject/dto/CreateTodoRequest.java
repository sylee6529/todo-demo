package com.example.todoproject.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateTodoRequest {
    
    @NotNull(message = "User ID는 필수입니다")
    private Long userId;
    
    @NotBlank(message = "제목은 필수입니다")
    private String title;
    
    private String content;
}