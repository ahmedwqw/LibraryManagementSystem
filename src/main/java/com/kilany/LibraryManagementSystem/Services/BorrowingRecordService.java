package com.kilany.LibraryManagementSystem.Services;

import com.kilany.LibraryManagementSystem.Entites.BorrowingRecord;
import com.kilany.LibraryManagementSystem.Exceptions.ResourceNotFoundException;
import com.kilany.LibraryManagementSystem.Repositories.BorrowingRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class BorrowingRecordService {

    @Autowired
    private BorrowingRecordRepository borrowingRecordRepository;

    public List<BorrowingRecord> getAllBorrowingRecords() {
        return borrowingRecordRepository.findAll();
    }

    @Cacheable("borrowingRecords")
    public BorrowingRecord getBorrowingRecordById(Long id) {
        return borrowingRecordRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("BorrowingRecord not found with id: " + id));
    }

    @Transactional
    @CachePut(value = "borrowingRecords", key = "#borrowingRecord.id")
    public BorrowingRecord saveBorrowingRecord(BorrowingRecord borrowingRecord) {
        // Set borrow date as current date
        borrowingRecord.setBorrowDate(LocalDate.now().toString());

        // Set return date as borrow date + 14 days (assuming a 14-day borrowing period)
        borrowingRecord.setReturnDate(LocalDate.now().plusDays(14).toString());
        return borrowingRecordRepository.save(borrowingRecord);
    }

    @CacheEvict(value = "borrowingRecords", key = "#id")
    public void deleteBorrowingRecord(Long id) {
        borrowingRecordRepository.deleteById(id);
    }
}
