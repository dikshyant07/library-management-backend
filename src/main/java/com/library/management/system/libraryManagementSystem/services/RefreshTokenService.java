package com.library.management.system.libraryManagementSystem.services;

import com.library.management.system.libraryManagementSystem.dtos.ApiResponse;
import com.library.management.system.libraryManagementSystem.dtos.TokenRefreshRequestDto;
import com.library.management.system.libraryManagementSystem.dtos.TokenRefreshResponseDto;
import com.library.management.system.libraryManagementSystem.exceptions.RefreshTokenDoesNotExistsException;
import com.library.management.system.libraryManagementSystem.exceptions.TokenExpiredException;
import com.library.management.system.libraryManagementSystem.exceptions.UserDoesNotExistsException;
import com.library.management.system.libraryManagementSystem.models.RefreshToken;
import com.library.management.system.libraryManagementSystem.models.User;
import com.library.management.system.libraryManagementSystem.repositories.RefreshTokenRepository;
import com.library.management.system.libraryManagementSystem.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;

    public RefreshTokenService(RefreshTokenRepository refreshTokenRepository, UserRepository userRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.userRepository = userRepository;
    }

    private RefreshToken generateRefreshToken(User user) {
        return RefreshToken.builder()
                .token(UUID.randomUUID().toString())
                .expiry(Instant.now().plusSeconds(24 * 60 * 60))
                .user(user)
                .build();

    }

    public RefreshToken getRefreshToken(String username) {
        User user = userRepository.findByEmail(username).orElseThrow(() -> new UserDoesNotExistsException("User with this username does not exists"));
        Optional<RefreshToken> refreshToken = refreshTokenRepository.findByUser(user);
        refreshToken.ifPresent(refreshTokenRepository::delete);
        RefreshToken newRefreshToken = generateRefreshToken(user);
        return refreshTokenRepository.save(newRefreshToken);
    }

    public ApiResponse<TokenRefreshResponseDto> refreshTheToken(TokenRefreshRequestDto requestDto) {
        RefreshToken token = refreshTokenRepository.findByToken(requestDto.getToken()).orElseThrow(() -> new RefreshTokenDoesNotExistsException("Refresh token is not found in the database"));
        if (token.getExpiry().isBefore(Instant.now())) {
            refreshTokenRepository.delete(token);
            throw new TokenExpiredException("Your refresh token is expired,please login lo get new one");
        }
        RefreshToken refreshToken = getRefreshToken(token.getUser().getEmail());
        refreshTokenRepository.delete(token);
        RefreshToken savedRefreshToken = refreshTokenRepository.save(refreshToken);
        TokenRefreshResponseDto refreshResponseDto = TokenRefreshResponseDto.builder()
                .accessToken("Access Token")
                .refreshToken(savedRefreshToken.getToken())
                .build();
        return ApiResponse.<TokenRefreshResponseDto>builder()
                .status(true)
                .httpStatus(HttpStatus.CREATED)
                .message("Successfully refreshed the token")
                .reponseObject(refreshResponseDto)
                .build();
    }

}
