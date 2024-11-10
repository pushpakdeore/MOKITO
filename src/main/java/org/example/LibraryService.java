package org.example;

public class LibraryService {

    private final BookRepository bookRepository;


    public LibraryService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    public String borrowBook(Long bookId) {
        Book book = bookRepository.findById(bookId);
        if (book == null) {
            return "Book not found.";
        }
        if (book.isAvailable()) {
            book.setAvailable(false);
            bookRepository.save(book);
            return "You have borrowed the book successfully.";
        } else {
            return "This book is already borrowed.";
        }
    }

    public String returnBook(Long bookId) {
        Book book = bookRepository.findById(bookId);
        if (book == null) {
            return "Book not found.";
        }
        if (!book.isAvailable()) {
            book.setAvailable(true);
            bookRepository.save(book);
            return "You have returned the book successfully.";
        } else {
            return "This book is already available.";
        }
    }
}
