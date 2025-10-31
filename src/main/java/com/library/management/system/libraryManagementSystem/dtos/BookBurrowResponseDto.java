package com.library.management.system.libraryManagementSystem.dtos;

import com.library.management.system.libraryManagementSystem.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookBurrowResponseDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate burrowedDate;
    private LocalDate dueDate;
    @Enumerated(EnumType.STRING)
    private Status status;
    private String userName;
    private String bookName;
}
