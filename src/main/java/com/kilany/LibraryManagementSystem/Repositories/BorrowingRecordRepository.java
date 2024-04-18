package com.kilany.LibraryManagementSystem.Repositories;

import com.kilany.LibraryManagementSystem.Entites.BorrowingRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowingRecordRepository extends JpaRepository<BorrowingRecord, Long> {

}
