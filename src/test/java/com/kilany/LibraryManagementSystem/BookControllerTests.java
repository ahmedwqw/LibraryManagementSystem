package com.kilany.LibraryManagementSystem;

import com.kilany.LibraryManagementSystem.Entites.Book;
import com.kilany.LibraryManagementSystem.Controllers.BookController;
import com.kilany.LibraryManagementSystem.Services.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class BookControllerTests {

    @InjectMocks
    private BookController bookController;

    @Mock
    private BookService bookService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllBooks() {
        List<Book> mockBooks = new ArrayList<>();
        mockBooks.add(new Book());
        mockBooks.add(new Book());

        when(bookService.getAllBooks()).thenReturn(mockBooks);

        ResponseEntity<List<Book>> response = bookController.getAllBooks();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
    }

    @Test
    public void testGetBookById() {
        Book mockBook = new Book();
        mockBook.setId(1L);

        when(bookService.getBookById(1L)).thenReturn(mockBook);

        ResponseEntity<Book> response = bookController.getBookById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1L, response.getBody().getId());
    }

    @Test
    public void testSaveBook() {
        Book bookToSave = new Book();
        bookToSave.setId(1L);

        when(bookService.saveBook(bookToSave)).thenReturn(bookToSave);

        ResponseEntity<Book> response = bookController.addBook(bookToSave);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(1L, response.getBody().getId());
    }


    @Test
    void testUpdateBook() {
        // Mock data
        Long bookId = 1L;
        Book updatedBook = new Book();
        updatedBook.setId(bookId);
        updatedBook.setTitle("Updated Title");
        updatedBook.setAuthor("Updated Author");
        updatedBook.setIsbn("Updated ISBN");
        updatedBook.setDescription("Updated Description");
        updatedBook.setPublicationDate("2022-04-18");
        updatedBook.setLanguage("English");

        // Mock the behavior of bookService.updateBook
        when(bookService.updateBook(eq(bookId), any(Book.class))).thenReturn(updatedBook);

        // Call the method to test
        ResponseEntity<Book> responseEntity = bookController.updateBook(updatedBook);

        // Verify the result
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(updatedBook, responseEntity.getBody());

        // Verify that bookService.updateBook was called with the correct arguments
        verify(bookService, times(1)).updateBook(eq(bookId), eq(updatedBook));
    }

    @Test
    public void testDeleteBook() {
        doNothing().when(bookService).deleteBook(1L);

        ResponseEntity<Void> response = bookController.deleteBook(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

}
