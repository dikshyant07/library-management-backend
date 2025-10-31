package com.library.management.system.libraryManagementSystem.mappers;

import com.library.management.system.libraryManagementSystem.dtos.BookBurrowResponseDto;
import com.library.management.system.libraryManagementSystem.models.Burrow;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BurrowMapper {
    @Mapping(target = "userName", source = "user.name")
    @Mapping(target = "bookName", source = "book.title")
    BookBurrowResponseDto toDto(Burrow burrow);


}
