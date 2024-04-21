package com.kilany.LibraryManagementSystem.Services;

import com.kilany.LibraryManagementSystem.Entites.Book;
import com.kilany.LibraryManagementSystem.Entites.BorrowingRecord;
import com.kilany.LibraryManagementSystem.Entites.Patron;
import com.kilany.LibraryManagementSystem.Exceptions.ResourceNotFoundException;
import com.kilany.LibraryManagementSystem.Repositories.BorrowingRecordRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BorrowingRecordServiceTest {

    @Mock
    private BorrowingRecordRepository borrowingRecordRepository;

    private BorrowingRecordService borrowingRecordService;


    @BeforeEach
    void setUp() {
        borrowingRecordService = new BorrowingRecordService(borrowingRecordRepository);
    }


    @Test
    void itShouldGetAllBorrowingRecords() {
        borrowingRecordService.getAllBorrowingRecords();

        verify(borrowingRecordRepository,times(1)).findAll();
    }

    @Test
    void getBorrowingRecordById_found() {
        Long recordId = 1L;

        //given
        BorrowingRecord record = new BorrowingRecord();
        record.setId(recordId);

        when(borrowingRecordRepository.findById(recordId)).thenReturn(Optional.of(record));

        BorrowingRecord result = borrowingRecordService.getBorrowingRecordById(recordId);

        assertNotNull(result);
        assertEquals(recordId,result.getId());

        verify(borrowingRecordRepository,times(1)).findById(recordId);



    }

    @Test
    void getBorrowingRecordById_NotFound() {
        Long recordId = 1L;

        //given
        BorrowingRecord record = new BorrowingRecord();
        record.setId(recordId);

        when(borrowingRecordRepository.findById(recordId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                ()-> borrowingRecordService.getBorrowingRecordById(recordId)
        );

        verify(borrowingRecordRepository,times(1)).findById(recordId);

    }

    @Test
    void itShouldSaveBorrowingRecord() {

        //given

        BorrowingRecord borrowingRecord = new BorrowingRecord();
        Book book = new Book();
        Patron patron = new Patron();
        borrowingRecord.setBook(book);
        borrowingRecord.setPatron(patron);


        LocalDate currentDate = LocalDate.now();
        LocalDate expectedReturnDate = currentDate.plusDays(14);

        borrowingRecord.setBorrowDate(currentDate.toString());
        borrowingRecord.setReturnDate(expectedReturnDate.toString());


        when(borrowingRecordRepository.save(borrowingRecord)).thenReturn(borrowingRecord);

        // Call the saveBorrowingRecord method
        BorrowingRecord savedRecord = borrowingRecordService.saveBorrowingRecord(borrowingRecord);

        // Assertions to ensure dates are set correctly
        assertEquals(currentDate.toString(), savedRecord.getBorrowDate());
        assertEquals(expectedReturnDate.toString(), savedRecord.getReturnDate());

        // Verify the repository's save method was called with the updated borrowing record
        verify(borrowingRecordRepository, times(1)).save(borrowingRecord);
    }

    @Test
    void deleteBorrowingRecord() {

        Long borrowingRecordId = 1L;

        // Call the method to delete a borrowing record
        borrowingRecordService.deleteBorrowingRecord(borrowingRecordId);

        // Verify the repository's deleteById method was called with the correct ID
        verify(borrowingRecordRepository, times(1)).deleteById(borrowingRecordId);
    }

    }

