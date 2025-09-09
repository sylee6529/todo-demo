package com.example.todoproject.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum UserErrorCode implements ErrorCode {
    
    USER_NOT_FOUND("USER_NOT_FOUND", "해당 사용자를 찾을 수 없습니다", HttpStatus.NOT_FOUND),
    USER_ID_REQUIRED("USER_ID_REQUIRED", "사용자 ID는 필수입니다", HttpStatus.BAD_REQUEST),
    USER_ID_INVALID("USER_ID_INVALID", "유효하지 않은 사용자 ID입니다", HttpStatus.BAD_REQUEST);
    
    private final String code;
    private final String message;
    private final HttpStatus status;
}