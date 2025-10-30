package com.library.management.system.libraryManagementSystem.models;

import com.library.management.system.libraryManagementSystem.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Burrow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate burrowedDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    @Enumerated(EnumType.STRING)
    private Status status;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    private Book book;
    @OneToOne
    @JoinColumn(name = "fine_id", referencedColumnName = "id")
    private Fine fine;

    @PrePersist
    public void prePersist() {
        this.burrowedDate = LocalDate.now();
    }
}
