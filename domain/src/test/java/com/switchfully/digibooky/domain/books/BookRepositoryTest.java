package com.switchfully.digibooky.domain.books;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BookRepositoryTest {

    private final BookRepository bookRepository = new BookRepository();
    private List<Book> bookList;

    @BeforeEach
    void createAndFillBookList() {
        bookList = new ArrayList<>();
        bookList.addAll(List.of(
                new Book("isbn1", "title1", new Author("first1", "last1")),
                new Book("isbn2", "title2", new Author("first2", "last2")),
                new Book("isbn3", "title3", new Author("first3", "last3")),
                new Book("isbn4", "title4", new Author("first4", "last4"))
        ));
        bookList.forEach(bookRepository::saveBook);
    }

    @Test
    void saveBookAddsBookToRepository() {
        Book book = new Book("isbn", "title", new Author("Firstname", "Lastname"));

        bookRepository.saveBook(book);

        assertThat(bookRepository.getAllBooks()).contains(book);
    }

    @Test
    void getAllBooksReturnsAllBooksFromBookRepository() {
        assertThat(bookRepository.getAllBooks()).containsAll(bookList);
    }

    @AfterEach
    void deleteAllBooksInBookRepository() {
        bookRepository.getAllBooks().clear();
    }
}
