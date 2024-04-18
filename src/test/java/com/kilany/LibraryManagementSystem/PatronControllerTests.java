package com.kilany.LibraryManagementSystem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import com.kilany.LibraryManagementSystem.Entites.Book;
import com.kilany.LibraryManagementSystem.Exceptions.ResourceNotFoundException;
import com.kilany.LibraryManagementSystem.Entites.Patron;
import com.kilany.LibraryManagementSystem.Controllers.PatronController;
import com.kilany.LibraryManagementSystem.Services.PatronService;
import com.kilany.LibraryManagementSystem.utitlity.MembershipType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class PatronControllerTests {

    @Mock
    private PatronService patronService;

    @InjectMocks
    private PatronController patronController;

    private Patron testPatron;

    @BeforeEach
    public void setUp() {
        Patron testPatron = new Patron();
        testPatron.setName("John Doe");
        testPatron.setEmail("john@example.com");
        testPatron.setPhoneNumber("1234567890");
        testPatron.setAddress("123 Main St, City");
        testPatron.setDateOfBirth("1990-01-01"); // Format: YYYY-MM-DD
        testPatron.setMembershipType(MembershipType.STANDARD.toString()); // Assuming MembershipType enum is defined
        testPatron.setMembershipExpiryDate("2023-01-01"); // Format: YYYY-MM-DD
        testPatron.setMembershipExpired(false);
    }

    @Test
    public void testAddPatron() {
        // Mock the behavior of PatronService
        when(patronService.savePatron(any(Patron.class))).thenReturn(testPatron);

        // Call the controller method
        ResponseEntity<Patron> responseEntity = patronController.addPatron(testPatron);

        // Verify the response
        assert responseEntity.getStatusCode() == HttpStatus.CREATED;
        assert responseEntity.getBody().getId() == 1L;
        // Assert other attributes as needed
    }

    @Test
    public void testUpdatePatron() {
        // Mock the behavior of PatronService
        when(patronService.updatePatron(eq(1L), any(Patron.class))).thenReturn(testPatron);
        when(patronService.updatePatron(eq(2L), any(Patron.class))).thenThrow(new ResourceNotFoundException("Patron not found"));

        // Call the controller method with existing ID
        ResponseEntity<Book> responseEntity1 = patronController.updatePatron(testPatron);
        // Verify the response
        assert responseEntity1.getStatusCode() == HttpStatus.OK;

        // Call the controller method with non-existing ID
        ResponseEntity<Book> responseEntity2 = patronController.updatePatron(testPatron);
        // Verify the response
        assert responseEntity2.getStatusCode() == HttpStatus.NOT_FOUND;
    }

    @Test
    public void testDeleteBook() {
        doNothing().when(patronService).deletePatron(1L);

        ResponseEntity<Void> response = patronController.deletePatron(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}
