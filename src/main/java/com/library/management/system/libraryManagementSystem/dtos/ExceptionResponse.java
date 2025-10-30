package com.library.management.system.libraryManagementSystem.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExceptionResponse {
    private String exceptionName;
    private String exceptionTime;
    private String detailedMessage;
    private Object validatonErrors;

}
