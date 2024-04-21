package com.kilany.LibraryManagementSystem.Entites;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Patron {

    public Patron(Long id, String name, String email, String phoneNumber, String address, String dateOfBirth, String membershipType) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.membershipType = membershipType;
    }
    public Patron( String name, String email, String phoneNumber, String address, String dateOfBirth, String membershipType) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.membershipType = membershipType;
    }

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
