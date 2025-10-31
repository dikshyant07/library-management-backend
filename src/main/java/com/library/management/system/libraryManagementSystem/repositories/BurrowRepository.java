package com.library.management.system.libraryManagementSystem.repositories;

import com.library.management.system.libraryManagementSystem.models.Burrow;
import com.library.management.system.libraryManagementSystem.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BurrowRepository extends JpaRepository<Burrow, Long> {

    int countByUser(User user);
}
