package com.library.management.system.libraryManagementSystem.repositories;

import com.library.management.system.libraryManagementSystem.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findByTitle(String title);

    boolean existsByTitle(String title);
}
