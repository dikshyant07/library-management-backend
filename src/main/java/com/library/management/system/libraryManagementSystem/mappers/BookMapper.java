package com.library.management.system.libraryManagementSystem.mappers;

import com.library.management.system.libraryManagementSystem.dtos.BookCreationRequestDto;
import com.library.management.system.libraryManagementSystem.dtos.BookCreationResponseDto;
import com.library.management.system.libraryManagementSystem.models.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "burrows", ignore = true)
    @Mapping(target = "category", ignore = true)
    Book toBook(BookCreationRequestDto requestDto);

    @Mapping(target = "category", source = "category.name")
    BookCreationResponseDto toDto(Book book);
}
