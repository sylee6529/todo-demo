package com.example.todoproject.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum TodoErrorCode implements ErrorCode {
    
    TODO_NOT_FOUND("TODO_NOT_FOUND", "해당 Todo를 찾을 수 없습니다", HttpStatus.NOT_FOUND),
    TODO_TITLE_REQUIRED("TODO_TITLE_REQUIRED", "Todo 제목은 필수입니다", HttpStatus.BAD_REQUEST),
    TODO_TITLE_TOO_LONG("TODO_TITLE_TOO_LONG", "Todo 제목은 200자를 초과할 수 없습니다", HttpStatus.BAD_REQUEST),
    TODO_CONTENT_TOO_LONG("TODO_CONTENT_TOO_LONG", "Todo 내용은 5000자를 초과할 수 없습니다", HttpStatus.BAD_REQUEST),
    TODO_ACCESS_DENIED("TODO_ACCESS_DENIED", "해당 Todo에 접근할 권한이 없습니다", HttpStatus.FORBIDDEN),
    TODO_UPDATE_EMPTY("TODO_UPDATE_EMPTY", "수정할 내용이 없습니다", HttpStatus.BAD_REQUEST);
    
    private final String code;
    private final String message;
    private final HttpStatus status;
}