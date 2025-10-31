package com.library.management.system.libraryManagementSystem.services;

import com.library.management.system.libraryManagementSystem.dtos.ApiResponse;
import com.library.management.system.libraryManagementSystem.dtos.BookCreationRequestDto;
import com.library.management.system.libraryManagementSystem.dtos.BookCreationResponseDto;
import com.library.management.system.libraryManagementSystem.exceptions.BookAlreadyExistsException;
import com.library.management.system.libraryManagementSystem.exceptions.CategoryDoesNotExistsException;
import com.library.management.system.libraryManagementSystem.mappers.BookMapper;
import com.library.management.system.libraryManagementSystem.models.Book;
import com.library.management.system.libraryManagementSystem.models.Category;
import com.library.management.system.libraryManagementSystem.repositories.BookRepository;
import com.library.management.system.libraryManagementSystem.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;
    private final BookMapper bookMapper;

    @Autowired
    public BookService(BookRepository bookRepository, CategoryRepository categoryRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.categoryRepository = categoryRepository;
        this.bookMapper = bookMapper;
    }

    public ApiResponse<BookCreationResponseDto> addBook(BookCreationRequestDto requestDto) {
        if (bookRepository.existsByTitle(requestDto.getTitle())) {
            throw new BookAlreadyExistsException("The book with this title already exists");
        }
        Category category = categoryRepository.findByName(requestDto.getCategory()).orElseThrow(() -> new CategoryDoesNotExistsException("Category with this name does not exists"));
        Book book = bookMapper.toBook(requestDto);
        book.setCategory(category);
        BookCreationResponseDto responseDto = bookMapper.toDto(bookRepository.save(book));
        return ApiResponse.<BookCreationResponseDto>builder()
                .status(true)
                .httpStatus(HttpStatus.CREATED)
                .message("Successfully created the book")
                .reponseObject(responseDto)
                .build();
    }
}



