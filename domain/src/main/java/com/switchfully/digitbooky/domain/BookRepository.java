package com.switchfully.digitbooky.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
@Repository
public class BookRepository {
    private final Map<String, Book> bookRepository;
    private final Logger log = LoggerFactory.getLogger(BookRepository.class);

    public BookRepository() {
        this.bookRepository = new HashMap<>();
    }

    public void saveBook(Book book) {
        bookRepository.put(book.getIsbn(), book);
    }

    public Collection<Book> getAllBooks() {
        log.debug("GET -> Handling request for all books");
        return bookRepository.values();
    }
}
