package com.library.management.system.libraryManagementSystem.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookBurrowRequestDto {
    private Long userId;
    private Long bookId;
    private List<Long> booksIds;
}
