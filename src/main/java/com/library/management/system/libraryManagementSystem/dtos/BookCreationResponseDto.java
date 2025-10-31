package com.library.management.system.libraryManagementSystem.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookCreationResponseDto {
    public String isbn;
    public int availableCopies;
    public boolean availability;
    public int totalCopies;
    private Long id;
    private String title;
    private String author;
    private int publishedYear;
    private String category;
}
