package com.library.management.system.libraryManagementSystem.mappers;

import com.library.management.system.libraryManagementSystem.dtos.CategoryCreationRequestDto;
import com.library.management.system.libraryManagementSystem.dtos.CategoryCreationResponseDto;
import com.library.management.system.libraryManagementSystem.models.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "books", ignore = true)
    Category toCategory(CategoryCreationRequestDto requestDto);

    CategoryCreationResponseDto toDto(Category category);
}
