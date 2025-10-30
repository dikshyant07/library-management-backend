package com.library.management.system.libraryManagementSystem.exceptions;

import com.library.management.system.libraryManagementSystem.dtos.ApiResponse;
import com.library.management.system.libraryManagementSystem.dtos.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<ExceptionResponse>> handleRuntimeExceptions(Exception e) {
        HttpStatus httpStatus;
        httpStatus = HttpStatus.BAD_REQUEST;
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .exceptionName(e.getClass().getSimpleName())
                .exceptionTime(LocalDateTime.now().toString())
                .detailedMessage(e.getMessage())
                .validatonErrors(null)
                .build();
        ApiResponse<ExceptionResponse> apiResponse = ApiResponse.<ExceptionResponse>builder()
                .status(false)
                .httpStatus(httpStatus)
                .message(String.format("%s exception occurred", exceptionResponse.getExceptionName()))
                .reponseObject(exceptionResponse)
                .build();
        return new ResponseEntity<>(apiResponse, httpStatus);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<ExceptionResponse>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        Map<String, String> errorMap = fieldErrors.stream()
                .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .exceptionName(e.getClass().getSimpleName())
                .exceptionTime(LocalDateTime.now().toString())
                .detailedMessage(e.getMessage())
                .validatonErrors(errorMap)
                .build();
        ApiResponse<ExceptionResponse> apiResponse = ApiResponse.<ExceptionResponse>builder()
                .status(false)
                .httpStatus(httpStatus)
                .message(String.format("%s exception occurred", exceptionResponse.getExceptionName()))
                .reponseObject(exceptionResponse)
                .build();
        return new ResponseEntity<>(apiResponse, httpStatus);
    }
}
