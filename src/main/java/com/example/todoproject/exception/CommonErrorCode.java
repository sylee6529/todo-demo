package com.example.todoproject.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum CommonErrorCode implements ErrorCode {
    
    INVALID_INPUT_VALUE("INVALID_INPUT_VALUE", "입력값이 올바르지 않습니다", HttpStatus.BAD_REQUEST),
    INVALID_PATH_VARIABLE("INVALID_PATH_VARIABLE", "경로 변수가 올바르지 않습니다", HttpStatus.BAD_REQUEST),
    MISSING_REQUEST_PARAMETER("MISSING_REQUEST_PARAMETER", "필수 요청 파라미터가 누락되었습니다", HttpStatus.BAD_REQUEST),
    METHOD_NOT_ALLOWED("METHOD_NOT_ALLOWED", "허용되지 않은 HTTP 메서드입니다", HttpStatus.METHOD_NOT_ALLOWED),
    INTERNAL_SERVER_ERROR("INTERNAL_SERVER_ERROR", "서버 내부 오류가 발생했습니다", HttpStatus.INTERNAL_SERVER_ERROR);
    
    private final String code;
    private final String message;
    private final HttpStatus status;
}