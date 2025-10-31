package com.library.management.system.libraryManagementSystem.services;

import com.library.management.system.libraryManagementSystem.dtos.ApiResponse;
import com.library.management.system.libraryManagementSystem.dtos.LoginRequestDto;
import com.library.management.system.libraryManagementSystem.dtos.LoginResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final RefreshTokenService refreshTokenService;
    private final JwtService jwtService;

    @Autowired
    public AuthService(AuthenticationManager authenticationManager, RefreshTokenService refreshTokenService, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.refreshTokenService = refreshTokenService;
        this.jwtService = jwtService;
    }

    public ApiResponse<LoginResponseDto> authenticateUser(LoginRequestDto loginRequestDto) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDto.getEmail(), loginRequestDto.getPassword()));
            String accessToken = jwtService.generateJwt(loginRequestDto.getEmail());
            String refreshToken = refreshTokenService.getRefreshToken(loginRequestDto.getEmail()).getToken();
            LoginResponseDto loginResponseDto = LoginResponseDto.builder()
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .build();
            return ApiResponse.<LoginResponseDto>builder()
                    .status(true)
                    .httpStatus(HttpStatus.ACCEPTED)
                    .message("Successfully logged in")
                    .reponseObject(loginResponseDto)
                    .build();
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Please enter valid username and password to login");

        }
    }
}
