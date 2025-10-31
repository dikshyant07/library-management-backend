package com.library.management.system.libraryManagementSystem.repositories;

import com.library.management.system.libraryManagementSystem.models.RefreshToken;
import com.library.management.system.libraryManagementSystem.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, User> {
    boolean existsByUser(User user);

    Optional<RefreshToken> findByUser(User user);

    Optional<RefreshToken> findByToken(String token);

    void deleteByToken(String token);
}
