package com.library.management.system.libraryManagementSystem.services;

import com.library.management.system.libraryManagementSystem.dtos.ApiResponse;
import com.library.management.system.libraryManagementSystem.dtos.CategoryCreationRequestDto;
import com.library.management.system.libraryManagementSystem.dtos.CategoryCreationResponseDto;
import com.library.management.system.libraryManagementSystem.exceptions.CategoryAlreadyExistsException;
import com.library.management.system.libraryManagementSystem.mappers.CategoryMapper;
import com.library.management.system.libraryManagementSystem.models.Category;
import com.library.management.system.libraryManagementSystem.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    public ApiResponse<CategoryCreationResponseDto> addBooksCategory(CategoryCreationRequestDto requestDto) {
        if (categoryRepository.existsByName(requestDto.getName())) {
            throw new CategoryAlreadyExistsException("This category already exists and duplicate not allowed");
        }
        Category category = categoryMapper.toCategory(requestDto);
        CategoryCreationResponseDto responseDto = categoryMapper.toDto(categoryRepository.save(category));
        return ApiResponse.<CategoryCreationResponseDto>builder()
                .status(true)
                .httpStatus(HttpStatus.CREATED)
                .message("Successfully added new category")
                .reponseObject(responseDto)
                .build();
    }

    public ApiResponse<String> deleteCategory(Long id) {
        categoryRepository.deleteById(id);
        return ApiResponse.<String>builder()
                .status(true)
                .httpStatus(HttpStatus.CREATED)
                .message("Successfully added new category")
                .reponseObject("Category with id " + id + " is deleted")
                .build();
    }
}
