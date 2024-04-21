package com.kilany.LibraryManagementSystem.Services;

import com.kilany.LibraryManagementSystem.Entites.Book;
import com.kilany.LibraryManagementSystem.Exceptions.ResourceNotFoundException;
import com.kilany.LibraryManagementSystem.Repositories.BookRepository;
import org.h2.command.dml.MergeUsing;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    private BookService bookService;

    @BeforeEach
    void setUp() {
        bookService = new BookService(bookRepository);
    }



    @Test
    void getAllBooks() {
        bookService.getAllBooks();
        verify(bookRepository,times(1)).findAll();
    }

    @Test
    void testGetBookById_Found() {
        //given
        Long bookId = 1L;
        Book book = new Book();
        book.setId(bookId);
        book.setTitle("Test Book");

        //   optional.of(book)الشرط ده انا الي بحطه يعني انا يجبر ال ميثود ترجع
        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));

        Book result= bookService.getBookById(bookId);

        // make sure the book returned from database is the same book I provide its id ;
        assertNotNull(result);
        assertEquals(bookId, result.getId());
        assertEquals("Test Book", result.getTitle());
        verify(bookRepository, times(1)).findById(bookId);
    }

    @Test
    void testGetBookById_NotFound(){
        //given
        Long bookId = 1L;
        Book book = new Book();
        book.setId(bookId);
        book.setTitle("Test Book");

        //   optional.empty الشرط ده انا الي بحطه يعني انا يجبر ال ميثود ترجع
        when(bookRepository.findById(bookId)).thenReturn(Optional.empty());

        // make sure that getBookById method throws an exception to type ResourceNotFoundException
        assertThrows(ResourceNotFoundException.class , ()-> bookService.getBookById(bookId));
        // make sure the bookRepository.findById()  run once
        verify(bookRepository,times(1)).findById(bookId);
    }

    @Test
    void saveBook() {
        //given
        Long bookId = 1L;
        Book book = new Book();
        book.setId(bookId);
        book.setTitle("Test Book");

        bookService.saveBook(book);

        //then
        ArgumentCaptor<Book> bookArgumentCaptor =                  // capture  of book to catch the book method save and compare with given book
                ArgumentCaptor.forClass(Book.class);

        verify(bookRepository).save(bookArgumentCaptor.capture());

        Book capturedBook = bookArgumentCaptor.getValue();

        assertEquals(book,capturedBook);

    }

    @Test
    void itShouldUpdateBook() {
        //given
        Long bookId = 1L;

        // Existing book to update
        Book existingBook = new Book();
        existingBook.setId(bookId);
        existingBook.setTitle("Original Title");
        existingBook.setAuthor("Original Author");
        existingBook.setIsbn("1234567890");
        existingBook.setDescription("Original Description");
        existingBook.setPublicationDate("2021-01-01");
        existingBook.setLanguage("English");

        // Updated book data
        Book updatedBook = new Book();
        updatedBook.setTitle("Updated Title");
        updatedBook.setAuthor("Updated Author");
        updatedBook.setIsbn("0987654321");
        updatedBook.setDescription("Updated Description");
        updatedBook.setPublicationDate("2021-01-01");
        updatedBook.setLanguage("Spanish");

        // Mock the repository to return the existing book when finding by ID
        when(bookRepository.findById(bookId)).thenReturn(Optional.of(existingBook));

        // Mock the repository save operation
        when(bookRepository.save(existingBook)).thenReturn(existingBook);

        // Call the updateBook method
        Book result = bookService.updateBook(bookId, updatedBook);

        assertEquals("Updated Title",result.getTitle());
        assertEquals("Updated Author", result.getAuthor());
        assertEquals("0987654321", result.getIsbn());
        assertEquals("Updated Description", result.getDescription());
        assertEquals("2021-01-01", result.getPublicationDate());
        assertEquals("Spanish", result.getLanguage());

        // Verify repository interactions
        verify(bookRepository, times(1)).findById(bookId);
        verify(bookRepository, times(1)).save(existingBook);


    }

    @Test
    void testUpdateBook_BookNotFound() {
        Long bookId = 2L;

        // Mock the repository to return empty when finding by ID
        when(bookRepository.findById(bookId)).thenReturn(Optional.empty());

        // Expecting a ResourceNotFoundException to be thrown
        assertThrows(ResourceNotFoundException.class, () -> bookService.updateBook(bookId, new Book()));

        // Verify repository interactions
        verify(bookRepository, times(1)).findById(bookId);
        verify(bookRepository, times(0)).save(any());


    }

    @Test
    void testDeleteBook() {
        Long bookId =1L;

        // Call deleteBook with a given ID
        bookService.deleteBook(bookId);

        // Verify repository interactions
        verify(bookRepository, times(1)).deleteById(bookId);
    }
}
