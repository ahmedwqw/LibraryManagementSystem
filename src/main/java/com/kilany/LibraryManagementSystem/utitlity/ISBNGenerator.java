package com.kilany.LibraryManagementSystem.utitlity;

import java.util.Random;

public class ISBNGenerator {

    // Method to generate a random ISBN-13
    public static String generateISBN() {
        // Generate a random 12-digit number
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 12; i++) {
            sb.append(random.nextInt(10));
        }

        // Calculate the check digit (ISBN-13 requires a check digit)
        String isbn12 = sb.toString();
        int sum = 0;
        for (int i = 0; i < 12; i++) {
            int digit = Character.getNumericValue(isbn12.charAt(i));
            sum += (i % 2 == 0) ? digit : digit * 3;
        }
        int checkDigit = (10 - (sum % 10)) % 10;

        // Append the check digit to the ISBN-12 to form the ISBN-13
        return isbn12 + checkDigit;
    }


}
