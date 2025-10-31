package com.library.management.system.libraryManagementSystem.controllers;

import com.library.management.system.libraryManagementSystem.dtos.*;
import com.library.management.system.libraryManagementSystem.services.BookService;
import com.library.management.system.libraryManagementSystem.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {
    private final BookService bookService;
    private final CategoryService categoryService;

    @Autowired
    public AdminController(BookService bookService, CategoryService categoryService) {
        this.bookService = bookService;
        this.categoryService = categoryService;
    }

    @PostMapping("/create-category")
    public ResponseEntity<ApiResponse<CategoryCreationResponseDto>> createCategory(@Valid @RequestBody CategoryCreationRequestDto requestDto) {
        ApiResponse<CategoryCreationResponseDto> categoryCreationResponseDtoApiResponse = categoryService.addBooksCategory(requestDto);
        return new ResponseEntity<>(categoryCreationResponseDtoApiResponse, categoryCreationResponseDtoApiResponse.getHttpStatus());

    }

    @DeleteMapping("/delete-category/{id}")
    public ResponseEntity<ApiResponse<String>> deleteCategory(@PathVariable(value = "id") Long id) {
        ApiResponse<String> stringApiResponse = categoryService.deleteCategory(id);
        return new ResponseEntity<>(stringApiResponse, stringApiResponse.getHttpStatus());
    }

    @PostMapping("/create-book")
    public ResponseEntity<ApiResponse<BookCreationResponseDto>> addBook(@RequestBody @Valid BookCreationRequestDto requestDto) {
        ApiResponse<BookCreationResponseDto> bookCreationResponseDtoApiResponse = bookService.addBook(requestDto);
        return new ResponseEntity<>(bookCreationResponseDtoApiResponse, bookCreationResponseDtoApiResponse.getHttpStatus());
    }
}
