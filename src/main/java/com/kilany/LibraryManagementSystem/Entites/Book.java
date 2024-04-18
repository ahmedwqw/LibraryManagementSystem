package com.kilany.LibraryManagementSystem.Entites;

import com.kilany.LibraryManagementSystem.Entites.BorrowingRecord;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;


import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Author is required")
    private String author;

    @NotBlank(message = "ISBN is required")
    private String isbn;

    private String publisher;

    @NotBlank(message = "Publication date is required")
    private String publicationDate;

    private String description;

    @NotBlank(message = "Language is required")
    private String language;

    @PositiveOrZero(message = "Number of pages must be a positive number or zero")
    private int numberOfPages;

    @Positive(message = "Price must be a positive number")
    private double price;

    private boolean available;

    private String coverPage;

    @OneToMany(mappedBy = "book")
    private Set<BorrowingRecord> borrowingRecords = new HashSet<>();
}
