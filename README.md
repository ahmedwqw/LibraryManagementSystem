# LibraryManagementSystem

# first:
    Edit the database configuration in application.yml file
    if you use MYSQl database just edit     url: jdbc:mysql://localhost:3306/<your database name>?useSSL=false&serverTimezone=UTC
                                            username: <your userName>
                                            password: <Your Password>
# second:
    inset the data in testDate.sql file in the resources' directory to database to help test the API Endpoints

# third:
   Use basic authentication with username and password.
         you can use ( user and userPass)  as username and password
          or         (admin and adminPass)  as username and password

# last:
    use postMan to Interacting with API Endpoints:
        Books Endpoints:
            GET /api/books: Retrieve all books.
            GET /api/books/{id}: Retrieve a book by ID.
            POST /api/books: Add a new book.
            PUT /api/books/{id}: Update an existing book.
            DELETE /api/books/{id}: Delete a book by ID.
        Patrons Endpoints:
            GET /api/patrons: Retrieve all patrons.
            GET /api/patrons/{id}: Retrieve a patron by ID.
            POST /api/patrons: Add a new patron.
            PUT /api/patrons/{id}: Update an existing patron.
            DELETE /api/patrons/{id}: Delete a patron by ID.
        BorrowingRecord endpoints:
            GET /api/borrowing-records: Retrieve all borrowing records.
            GET /api/borrowing-records/{id}: Retrieve a borrowing record by ID.
            POST /api/borrowing-records: Create a new borrowing record (to lend a book).
            PUT /api/borrowing-records/{id}: Update an existing borrowing record (e.g., extend the due date).
            DELETE /api/borrowing-records/{id}: Delete a borrowing record (e.g., return a book).

     (Note the application run on localhost:4040  you can change the port from server.port in yml file)




Project Overview: We're building a Library Management System using Spring Boot.
# Entities:
    * Book: Represents a book in the library. Contains attributes like title, author, ISBN, etc.
    * Patron: Represents a library patron. Contains attributes like name, email, phone number, etc.
    * BorrowingRecord: Represents a record of a book being borrowed by a patron. Links books and patrons.
# Functionality:
    * CRUD operations for books and patrons.
    * Borrowing and returning books.
    * Error handling.
    * Input validation.
    * Logging.
    * Caching.
    * Security with basic authentication.
# Technologies Used:
   * Spring Boot for building RESTful APIs.
   * JPA (Java Persistence API) for database operations.
   * Hibernate as the JPA provider.
   * Spring Security for authentication and authorization.
   * Spring AOP (Aspect-Oriented Programming) for logging.
   * Mockito for unit testing.
   * Jakarta Validation for input validation.
# Project Structure:
   * Controllers: Handle incoming HTTP requests and delegate to services.
   * Services: Contain business logic and interact with repositories.
   * Repositories: Interface with the database.
   * Exceptions: Custom exception classes.
   * Utility: Contains utility classes, enums, etc.
   * Tests: Unit tests for controllers, services, and repositories.
# Security:
   * Basic authentication to protect API endpoints.
   * Custom login form.
   * Authorization based on user roles (USER, ADMIN).
# Testing:
   * Unit tests for controllers, services, and repositories using Mockito.
   * Integration tests for API endpoints.
