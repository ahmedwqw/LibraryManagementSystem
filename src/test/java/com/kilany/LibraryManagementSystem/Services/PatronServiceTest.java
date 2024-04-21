package com.kilany.LibraryManagementSystem.Services;

import com.kilany.LibraryManagementSystem.Entites.Patron;
import com.kilany.LibraryManagementSystem.Exceptions.ResourceNotFoundException;
import com.kilany.LibraryManagementSystem.Repositories.PatronRepository;
import com.kilany.LibraryManagementSystem.utitlity.MembershipType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class PatronServiceTest {


    @Mock
    private PatronRepository patronRepository;

    private  PatronService patronService;



    @BeforeEach
    void setUp() {
        patronService= new PatronService(patronRepository);
    }

    @Test
    void testGetAllPatrons() {

        // Arrange
        Patron patron1 = new Patron();
        Patron patron2 = new Patron();

        when(patronRepository.findAll()).thenReturn(Arrays.asList(patron1, patron2));

        // Act
        List<Patron> patrons = patronService.getAllPatrons();

        // Assert
        assertEquals(2, patrons.size());
        assertTrue(patrons.contains(patron1));
        assertTrue(patrons.contains(patron2));
        verify(patronRepository, times(1)).findAll();
    }

    @Test
    void testGetPatronById_Success() {
        //given
        Long patronId = 1L;
        Patron patron = new Patron();

        //mock
        when(patronRepository.findById(patronId)).thenReturn(Optional.of(patron));

        // method to test
        Patron retrievedPatron = patronService.getPatronById(patronId);

        // assertion
        assertNotNull(retrievedPatron);
        assertEquals(patron.getId(), retrievedPatron.getId());
        verify(patronRepository, times(1)).findById(patronId);
    }


    @Test
    void testGetPatronById_NotFound() {
        //given
        Long patronId = 1L;

        //mock
        when(patronRepository.findById(patronId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class ,
                ()->patronService.getPatronById(patronId));

        verify(patronRepository, times(1)).findById(patronId);
    }

    @Test
    void testSavePatron_whenHave1YearSubscription() {

        //given
        Patron patron = new Patron();
        patron.setMembershipType(MembershipType.STANDARD.toString());

        LocalDate expectedExpiryDate = LocalDate.now().plusYears(1);
        System.out.println(expectedExpiryDate);

        //mock
        when(patronRepository.save(patron)).thenReturn(patron);

        // method to test
        Patron savedPatron = patronService.savePatron(patron);
        System.out.println(savedPatron.getMembershipExpiryDate());

        // assertion
        assertEquals(expectedExpiryDate.format(DateTimeFormatter.ISO_LOCAL_DATE) , savedPatron.getMembershipExpiryDate());
        verify(patronRepository, times(1)).save(patron);
    }

    @Test
    void testSavePatron_whenHaveLifeTimeSubscription() {

        //given
        Patron patron = new Patron();
        patron.setMembershipType(MembershipType.LIFETIME.toString());

        LocalDate expectedExpiryDate = LocalDate.now().plusYears(100);
        System.out.println(expectedExpiryDate);

        //mock
        when(patronRepository.save(patron)).thenReturn(patron);

        // method to test
        Patron savedPatron = patronService.savePatron(patron);
        System.out.println(savedPatron.getMembershipExpiryDate());

        // assertion
        assertEquals(expectedExpiryDate.format(DateTimeFormatter.ISO_LOCAL_DATE) , savedPatron.getMembershipExpiryDate());
        verify(patronRepository, times(1)).save(patron);
    }

    @Test
    void testUpdatePatron_Success() {
        Long patronId = 1L;
        Patron existingPatron = new Patron( patronId,"John Doe", "john@example.com", "123456789", "123 Main St", "2000-01-01", "STANDARD");
        Patron updatedPatron = new Patron( "Updated Name", "updated@example.com", "987654321", "Updated Address", "1990-01-01", "STANDARD");

        when(patronRepository.findById(patronId)).thenReturn(Optional.of(existingPatron));
        when(patronRepository.save(existingPatron)).thenReturn(existingPatron);

        Patron result = patronService.updatePatron(patronId, updatedPatron);

        assertEquals("Updated Name", result.getName());
        assertEquals("updated@example.com", result.getEmail());
        assertEquals("987654321", result.getPhoneNumber());
        assertEquals("Updated Address", result.getAddress());
        assertEquals("1990-01-01", result.getDateOfBirth());
        assertEquals("STANDARD", result.getMembershipType());

        verify(patronRepository, times(1)).save(existingPatron);
        verify(patronRepository, times(1)).findById(patronId);
    }

    @Test
    void testUpdatePatron_NotFound() {
        Long patronId = 1L;
        Patron updatedPatron = new Patron(null, "Updated Name", "updated@example.com", "987654321", "Updated Address", "1990-01-01", "STANDARD");

        when(patronRepository.findById(patronId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            patronService.updatePatron(patronId, updatedPatron);
        });

        verify(patronRepository, times(1)).findById(patronId);
        verify(patronRepository, times(0)).save(any());
    }

    @Test
    void testDeletePatron_Success() {
        Long patronId = 1L;

        patronService.deletePatron(patronId);

        verify(patronRepository, times(1)).deleteById(patronId);
    }




}
