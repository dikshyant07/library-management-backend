package com.library.management.system.libraryManagementSystem.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginRequestDto {
    @Email(message = "Please enter valid email")
    @NotBlank(message = "Please enter valid email")
    private String email;
    @NotBlank(message = "Please provide non empty ,valid password")
    private String password;
}
