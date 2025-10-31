package com.library.management.system.libraryManagementSystem.dtos;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookCreationRequestDto {

    @NotBlank(message = "ISBN must not be blank")
    @Size(min = 10, max = 13, message = "ISBN must be between 10 and 13 characters")
    public String isbn;
    @NotNull(message = "Availability must be specified")
    public Boolean availability;

    @Positive(message = "Total copies must be greater than 0")
    public int totalCopies;

    @NotBlank(message = "Title must not be blank")
    private String title;

    @NotBlank(message = "Author must not be blank")
    private String author;

    @Min(value = 1450, message = "Published year must be after 1450")
    @Max(value = 2100, message = "Published year must be realistic")
    private int publishedYear;
    @NotBlank(message = "Category must not be blank")
    private String category;

}
