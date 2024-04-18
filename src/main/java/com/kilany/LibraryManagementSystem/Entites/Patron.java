package com.kilany.LibraryManagementSystem.Entites;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Patron {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;
    private String phoneNumber;
    private String address;
    private String dateOfBirth;
    private String membershipType;
    private String membershipExpiryDate;
    private Boolean membershipExpired;

    @OneToMany(mappedBy = "patron")
    private Set<BorrowingRecord> borrowingRecords = new HashSet<>();
}
