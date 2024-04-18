package com.kilany.LibraryManagementSystem.Controllers;

import com.kilany.LibraryManagementSystem.Services.BorrowingRecordService;
import com.kilany.LibraryManagementSystem.Entites.BorrowingRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/borrowing-records")
public class BorrowingRecordController {

    @Autowired
    private BorrowingRecordService borrowingRecordService;

    @GetMapping
    public ResponseEntity<List<BorrowingRecord>> getAllBorrowingRecords() {
        List<BorrowingRecord> borrowingRecords = borrowingRecordService.getAllBorrowingRecords();
        return new ResponseEntity<>(borrowingRecords, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BorrowingRecord> getBorrowingRecordById(@PathVariable Long id) {
        BorrowingRecord borrowingRecord = borrowingRecordService.getBorrowingRecordById(id);
        if (borrowingRecord == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(borrowingRecord, HttpStatus.OK);
    }

    // the method in Projects Attachments
    @PostMapping
    public ResponseEntity<BorrowingRecord> addBorrowingRecord(@RequestBody BorrowingRecord borrowingRecord) {
        BorrowingRecord savedBorrowingRecord = borrowingRecordService.saveBorrowingRecord(borrowingRecord);
        return new ResponseEntity<>(savedBorrowingRecord, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBorrowingRecord(@PathVariable Long id) {
        borrowingRecordService.deleteBorrowingRecord(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
