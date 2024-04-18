package com.kilany.LibraryManagementSystem.Repositories;

import com.kilany.LibraryManagementSystem.Entites.Patron;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatronRepository  extends JpaRepository<Patron, Long> {

}
