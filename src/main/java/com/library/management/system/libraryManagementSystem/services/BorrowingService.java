package com.library.management.system.libraryManagementSystem.services;

import com.library.management.system.libraryManagementSystem.dtos.ApiResponse;
import com.library.management.system.libraryManagementSystem.dtos.BookBurrowRequestDto;
import com.library.management.system.libraryManagementSystem.dtos.BookBurrowResponseDto;
import com.library.management.system.libraryManagementSystem.exceptions.BookNoAvaibabeException;
import com.library.management.system.libraryManagementSystem.exceptions.CannotBurrowException;
import com.library.management.system.libraryManagementSystem.exceptions.UserDoesNotExistsException;
import com.library.management.system.libraryManagementSystem.models.Book;
import com.library.management.system.libraryManagementSystem.models.User;
import com.library.management.system.libraryManagementSystem.repositories.BookRepository;
import com.library.management.system.libraryManagementSystem.repositories.BurrowRepository;
import com.library.management.system.libraryManagementSystem.repositories.CategoryRepository;
import com.library.management.system.libraryManagementSystem.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BorrowingService {
    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final BurrowRepository burrowRepository;

    @Autowired
    public BorrowingService(BookRepository bookRepository, CategoryRepository categoryRepository, UserRepository userRepository, BurrowRepository burrowRepository) {
        this.bookRepository = bookRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
        this.burrowRepository = burrowRepository;
    }

    public ApiResponse<BookBurrowResponseDto> burrowBook(BookBurrowRequestDto requestDto) {

        User member = userRepository.findById(requestDto.getUserId()).orElseThrow(() -> new UserDoesNotExistsException("User with this id does not exists"));
        List<Long> booksIds = requestDto.getBooksIds();
        int currentlyBurrowedBooks = burrowRepository.countByUser(member);
        if (currentlyBurrowedBooks >= 3 || requestDto.getBooksIds().size() > 3) {
            throw new CannotBurrowException("You have already burrowed " + currentlyBurrowedBooks + " so you cannot burrow more");
        }
        List<Book> books = bookRepository.findAllById(booksIds);
        List<Book> unavailableBooks = books.stream().filter(book -> !book.isAvailability()).toList();
        if (!unavailableBooks.isEmpty()) {
            throw new BookNoAvaibabeException(unavailableBooks.stream().map(Book::getTitle).collect(Collectors.joining(",")));
        }
        List<Book> availableBooks = booksIds.stream().map((id) -> bookRepository.findById(id).get()).toList();
        availableBooks.stream().

    }
}
