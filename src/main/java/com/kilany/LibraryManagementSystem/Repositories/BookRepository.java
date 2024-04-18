package com.kilany.LibraryManagementSystem.Repositories;

import com.kilany.LibraryManagementSystem.Entites.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

}
