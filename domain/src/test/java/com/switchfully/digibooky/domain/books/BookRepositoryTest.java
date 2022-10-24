package com.switchfully.digibooky.domain.books;

import com.switchfully.digibooky.domain.users.Address;
import com.switchfully.digibooky.domain.users.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
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

    @Test
    void getBookByISBN_GivenValidISBN() {
        Book expected = bookList.get(0);
        Book actual = bookRepository.getBookByISBN(expected.getIsbn());
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void getBookByISBN_GivenInvalidISBN() {
        assertThatThrownBy(() -> bookRepository.getBookByISBN("notAValidISBN"))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessageContaining("Book with ISBN notAValidISBN was not found");
    }

    @AfterEach
    void deleteAllBooksInBookRepository() {
        bookRepository.getAllBooks().clear();
    }
}
