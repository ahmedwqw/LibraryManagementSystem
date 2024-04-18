package com.kilany.LibraryManagementSystem.Services;

import com.kilany.LibraryManagementSystem.Entites.Patron;
import com.kilany.LibraryManagementSystem.Exceptions.ResourceNotFoundException;
import com.kilany.LibraryManagementSystem.Repositories.PatronRepository;
import com.kilany.LibraryManagementSystem.utitlity.MembershipType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class PatronService {

    @Autowired
    private PatronRepository patronRepository;

    public List<Patron> getAllPatrons() {
        return patronRepository.findAll();
    }

    @Cacheable("patrons")
    public Patron getPatronById(Long id) {
        return patronRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patron not found with id: " + id));
    }

    @Transactional
    @CachePut(value = "patrons", key = "#patron.id")
    public Patron savePatron(Patron patron) {
        // Check if the membershipType is STANDARD or STUDENT
        if (patron.getMembershipType() == MembershipType.STANDARD.toString() || patron.getMembershipType() == MembershipType.STUDENT.toString()) {
            // Set membershipExpiryDate as one year from now
            LocalDate expiryDate = LocalDate.now().plusYears(1);
            patron.setMembershipExpiryDate(expiryDate.format(DateTimeFormatter.ISO_LOCAL_DATE));
        } else {
            // lifetime membership
            LocalDate expiryDate = LocalDate.now().plusYears(100);
            patron.setMembershipExpiryDate(expiryDate.format(DateTimeFormatter.ISO_LOCAL_DATE));
        }
        return patronRepository.save(patron);
    }

    @Transactional
    public Patron updatePatron(Long id, Patron updatedPatron) {
        // Find the existing patron by id
        Patron existingPatron = patronRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patron not found with id: " + id));

        // Update the attributes of the existing patron
        existingPatron.setName(updatedPatron.getName());
        existingPatron.setEmail(updatedPatron.getEmail());
        existingPatron.setPhoneNumber(updatedPatron.getPhoneNumber());
        existingPatron.setAddress(updatedPatron.getAddress());
        existingPatron.setDateOfBirth(updatedPatron.getDateOfBirth());
        existingPatron.setMembershipType(updatedPatron.getMembershipType());
        existingPatron.setMembershipExpiryDate(updatedPatron.getMembershipExpiryDate());
        // Update other attributes as needed

        // Save the updated patron
        return patronRepository.save(existingPatron);
    }
    @CacheEvict(value = "patrons", key = "#id")
    public void deletePatron(Long id) {
        patronRepository.deleteById(id);
    }
}
