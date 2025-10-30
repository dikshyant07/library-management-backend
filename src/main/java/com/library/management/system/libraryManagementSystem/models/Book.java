package com.library.management.system.libraryManagementSystem.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "\"book\"")
public class Book {
    public String isbn;
    public int availableCopies;
    public boolean availability;
    public int totalCopies;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;
    private int publishedYear;
    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;
    @OneToMany(mappedBy = "book")
    private List<Burrow> burrows;

}
