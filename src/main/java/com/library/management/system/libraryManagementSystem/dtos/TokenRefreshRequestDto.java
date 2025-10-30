package com.library.management.system.libraryManagementSystem.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class TokenRefreshRequestDto {
    @NotBlank(message = "Please provide non blank refresh token")
    private String token;
}
