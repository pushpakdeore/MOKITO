package org.example;

interface BookRepository {
    Book findById(Long bookId);
    void save(Book book);
}

public class Book {
    private Long id;
    private boolean available;


    public Book(Long id, boolean available) {
        this.id = id;
        this.available = available;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}

