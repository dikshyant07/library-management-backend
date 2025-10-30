package com.library.management.system.libraryManagementSystem.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.library.management.system.libraryManagementSystem.dtos.ApiResponse;
import com.library.management.system.libraryManagementSystem.dtos.ExceptionResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    private final ObjectMapper objectMapper;

    @Autowired
    public CustomAccessDeniedHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setContentType("application/json");
        response.setStatus(403);
        String errorName = "AccessDeniedException";
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .exceptionName(errorName)
                .exceptionTime(LocalDateTime.now().toString())
                .detailedMessage(errorName + " exception occurred ")
                .validatonErrors(null)
                .build();
        ApiResponse<ExceptionResponse> apiResponse = ApiResponse.<ExceptionResponse>builder()
                .status(false)
                .httpStatus(HttpStatus.FORBIDDEN)
                .message(String.format("%s exception occurred", exceptionResponse.getExceptionName()))
                .reponseObject(exceptionResponse)
                .build();
        objectMapper.writeValue(response.getOutputStream(), apiResponse);
    }
}
