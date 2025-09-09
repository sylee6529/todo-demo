package com.example.todoproject.exception;

import lombok.Getter;

@Getter
public class TodoException extends RuntimeException {
    
    private final ErrorCode errorCode;
    
    public TodoException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
    
    public TodoException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
    
    public TodoException(ErrorCode errorCode, Throwable cause) {
        super(errorCode.getMessage(), cause);
        this.errorCode = errorCode;
    }
}