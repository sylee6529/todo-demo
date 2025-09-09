package com.example.todoproject.exception;

import com.example.todoproject.dto.CommonResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;


@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TodoException.class)
    public ResponseEntity<CommonResponse<Void>> handleTodoException(TodoException e) {
        log.error("TodoException: {}", e.getMessage(), e);
        ErrorCode errorCode = e.getErrorCode();
        return ResponseEntity.status(errorCode.getStatus())
                .body(CommonResponse.error(errorCode));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CommonResponse<Void>> handleMethodArgumentNotValid(MethodArgumentNotValidException e) {
        log.error("MethodArgumentNotValidException: {}", e.getMessage(), e);
        String message = e.getBindingResult().getFieldErrors().stream()
                .findFirst()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .orElse(CommonErrorCode.INVALID_INPUT_VALUE.getMessage());
        return ResponseEntity.badRequest()
                .body(CommonResponse.error(CommonErrorCode.INVALID_INPUT_VALUE.getCode(), message));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<CommonResponse<Void>> handleConstraintViolation(ConstraintViolationException e) {
        log.error("ConstraintViolationException: {}", e.getMessage(), e);
        String message = e.getConstraintViolations().stream()
                .findFirst()
                .map(ConstraintViolation::getMessage)
                .orElse(CommonErrorCode.INVALID_PATH_VARIABLE.getMessage());
        return ResponseEntity.badRequest()
                .body(CommonResponse.error(CommonErrorCode.INVALID_PATH_VARIABLE.getCode(), message));
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<CommonResponse<Void>> handleMissingServletRequestParameter(MissingServletRequestParameterException e) {
        log.error("MissingServletRequestParameterException: {}", e.getMessage(), e);
        String message = String.format("필수 파라미터 '%s'가 누락되었습니다", e.getParameterName());
        return ResponseEntity.badRequest()
                .body(CommonResponse.error(CommonErrorCode.MISSING_REQUEST_PARAMETER.getCode(), message));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<CommonResponse<Void>> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException e) {
        log.error("MethodArgumentTypeMismatchException: {}", e.getMessage(), e);
        String message = String.format("'%s' 파라미터의 값이 올바르지 않습니다", e.getName());
        return ResponseEntity.badRequest()
                .body(CommonResponse.error(CommonErrorCode.INVALID_PATH_VARIABLE.getCode(), message));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CommonResponse<Void>> handleException(Exception e) {
        log.error("Unexpected exception: {}", e.getMessage(), e);
        return ResponseEntity.internalServerError()
                .body(CommonResponse.error(CommonErrorCode.INTERNAL_SERVER_ERROR));
    }
}