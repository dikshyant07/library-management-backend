package com.library.management.system.libraryManagementSystem.mappers;

import com.library.management.system.libraryManagementSystem.dtos.RegistrationRequestDto;
import com.library.management.system.libraryManagementSystem.dtos.RegistrationResponseDto;
import com.library.management.system.libraryManagementSystem.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "burrows", ignore = true)
    User toUser(RegistrationRequestDto requestDto);

    @Mapping(target = "userId", source = "id")
    RegistrationResponseDto toDto(User user);
}
