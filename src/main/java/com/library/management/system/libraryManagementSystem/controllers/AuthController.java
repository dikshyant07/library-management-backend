package com.library.management.system.libraryManagementSystem.controllers;

import com.library.management.system.libraryManagementSystem.dtos.*;
import com.library.management.system.libraryManagementSystem.services.AuthService;
import com.library.management.system.libraryManagementSystem.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final UserService userService;
    private final AuthService authService;

    @Autowired
    public AuthController(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    @PostMapping("/registration")
    public ResponseEntity<ApiResponse<RegistrationResponseDto>> registerMember(@Valid @RequestBody RegistrationRequestDto requestDto) {
        ApiResponse<RegistrationResponseDto> apiResponse = userService.registerMember(requestDto);
        return new ResponseEntity<>(apiResponse, apiResponse.getHttpStatus());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<String>> deleteMemberById(@PathVariable Long id) {
        ApiResponse<String> stringApiResponse = userService.deleteMember(id);
        return new ResponseEntity<>(stringApiResponse, stringApiResponse.getHttpStatus());

    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponseDto>> authenticateUser(@Valid @RequestBody LoginRequestDto requestDto) {
        ApiResponse<LoginResponseDto> loginResponseDtoApiResponse = authService.authenticateUser(requestDto);
        return new ResponseEntity<>(loginResponseDtoApiResponse, loginResponseDtoApiResponse.getHttpStatus());
    }
}
