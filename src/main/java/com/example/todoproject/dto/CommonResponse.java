package com.example.todoproject.dto;

import com.example.todoproject.exception.ErrorCode;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommonResponse<T> {
    
    private final String code;
    private final String message;
    private final T data;

    public static <T> CommonResponse<T> success(T data) {
        return new CommonResponse<>("SUCCESS", "요청이 성공적으로 처리되었습니다", data);
    }
    
    public static <T> CommonResponse<T> success(String message, T data) {
        return new CommonResponse<>("SUCCESS", message, data);
    }

    public static <T> CommonResponse<T> error(ErrorCode errorCode) {
        return new CommonResponse<>(errorCode.getCode(), errorCode.getMessage(), null);
    }
    
    public static <T> CommonResponse<T> error(String code, String message) {
        return new CommonResponse<>(code, message, null);
    }
}