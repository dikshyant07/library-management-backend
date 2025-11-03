package com.library.management.system.libraryManagementSystem.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponse<T> {
    ArrayList list = new ArrayList();
    private boolean status;
    private HttpStatus httpStatus;
    private String message;
    private T reponseObject;
}
