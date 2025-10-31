package com.library.management.system.libraryManagementSystem.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class CategoryCreationRequestDto {
    @NotBlank(message = "Please enter valid category name")
    private String name;
    @NotBlank(message = "please provide valid category description")
    private String description;
}
