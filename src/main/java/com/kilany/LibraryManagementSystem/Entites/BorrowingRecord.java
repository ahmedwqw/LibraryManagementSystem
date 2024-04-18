package com.kilany.LibraryManagementSystem.Entites;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class BorrowingRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Book is required")
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @NotNull(message = "Patron is required")
    @ManyToOne
    @JoinColumn(name = "patron_id")
    private Patron patron;

    private String borrowDate;
    private String returnDate;
    private String actualReturnDate;


}
