package com.library.management.system.libraryManagementSystem.dtos;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class CategoryCreationResponseDto {
    private Long id;
    private String name;
    private String description;
}
