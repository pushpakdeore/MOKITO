package org.example;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

class LibraryServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private LibraryService libraryService;

    private Book availableBook;
    private Book borrowedBook;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Create test books
        availableBook = new Book(1L, true);
        borrowedBook = new Book(2L, false);
    }

    @Test
    void testBorrowBook_Success() {

        when(bookRepository.findById(1L)).thenReturn(availableBook);

        String result = libraryService.borrowBook(1L);


        assertFalse(availableBook.isAvailable(), "Book should be marked as borrowed");
        assertEquals("You have borrowed the book successfully.", result, "Message should indicate successful borrowing");

        verify(bookRepository).save(availableBook);
    }

    @Test
    void testBorrowBook_BookAlreadyBorrowed() {
        when(bookRepository.findById(2L)).thenReturn(borrowedBook);


        String result = libraryService.borrowBook(2L);


        assertFalse(borrowedBook.isAvailable(), "Book should remain unavailable");
        assertEquals("This book is already borrowed.", result, "Message should indicate the book is unavailable");

        verify(bookRepository, never()).save(borrowedBook);
    }

    @Test
    void testReturnBook_Success() {

        when(bookRepository.findById(2L)).thenReturn(borrowedBook);

        String result = libraryService.returnBook(2L);

        assertTrue(borrowedBook.isAvailable(), "Book should be marked as available");
        assertEquals("You have returned the book successfully.", result, "Message should indicate successful return");

        verify(bookRepository).save(borrowedBook);
    }

    @Test
    void testReturnBook_BookAlreadyAvailable() {

        when(bookRepository.findById(1L)).thenReturn(availableBook);

        String result = libraryService.returnBook(1L);


        assertTrue(availableBook.isAvailable(), "Book should remain available");
        assertEquals("This book is already available.", result, "Message should indicate the book is already available");

        verify(bookRepository, never()).save(availableBook);
    }
}
