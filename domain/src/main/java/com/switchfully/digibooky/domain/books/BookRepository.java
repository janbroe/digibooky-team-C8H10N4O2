package com.switchfully.digibooky.domain.books;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@Repository
public class BookRepository {
    private final Map<String, Book> bookRepository;
    private final Logger log = LoggerFactory.getLogger(BookRepository.class);

    public BookRepository() {
        this.bookRepository = new HashMap<>();
        saveBook(new Book("isbn1", "title1", new Author("first1", "last1")));
        saveBook(new Book("isbn2", "title2", new Author("first2", "last2")));
        saveBook(new Book("isbn3", "title3", new Author("first3", "last3")));
        saveBook(new Book("isbn4", "title4", new Author("first4", "last4")));
    }

    public void saveBook(Book book) {
        bookRepository.put(book.getIsbn(), book);
    }

    public Collection<Book> getAllBooks() {
        log.debug("GET -> Handling request for all books");
        return bookRepository.values();
    }

    public Book getBookByISBN(String isbn) {
        log.debug("GET -> Handling request for book with ISBN ".concat(isbn));
        if (!bookRepository.containsKey(isbn)) {
            throw new NoSuchElementException("Book with ISBN ".concat(isbn).concat(" was not found"));
        }
        return bookRepository.get(isbn);
    }
}
