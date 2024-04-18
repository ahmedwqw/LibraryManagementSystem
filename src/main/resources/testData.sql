use LibraryManagementSystemDb ;
INSERT INTO Book (title, author, isbn, publisher, publication_date,  description, language, number_of_pages, price, available, cover_page)
VALUES
    ('Example Book 1', 'Author 1', '978-3-16-148410-0', 'Publisher A', '2022-01-01',  'This is an example book.', 'English', 200, 29.99, true, 'cover1.jpg'),
    ('Example Book 2', 'Author 2', '978-3-16-148411-1', 'Publisher B', '2022-02-01',  'This is another example book.', 'English', 250, 39.99, true, 'cover2.jpg'),
    ('Example Book 3', 'Author 3', '978-3-16-148412-2', 'Publisher C', '2022-03-01',  'This is yet another example book.', 'English', 300, 49.99, true,  'cover3.jpg');


INSERT INTO Patron (name, email, phone_number, address, date_of_birth, membership_type, membership_expiry_date)
VALUES
    ('John Doe', 'john@example.com', '123-456-7890', '123 Main St, City, Country', '1990-01-01', 'STANDARD', '2025-01-01'),
    ('Jane Smith', 'jane@example.com', '987-654-3210', '456 Elm St, City, Country', '1985-05-15', 'STUDENT', '2025-05-15'),
    ('Alice Johnson', 'alice@example.com', '555-555-5555', '789 Oak St, City, Country', '1975-11-30', 'PREMIUM', '2125-11-30');


INSERT INTO Borrowing_Record (book_id, patron_id, borrow_date, return_date, actual_return_date)
VALUES
    (1, 1, '2024-05-10', '2024-05-24', NULL),
    (2, 2, '2024-05-12', '2024-05-26', NULL),
    (3, 3, '2024-05-15', '2024-05-29', NULL);
