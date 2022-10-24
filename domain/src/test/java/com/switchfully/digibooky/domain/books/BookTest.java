package com.switchfully.digibooky.domain.books;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BookTest {

    @Test
    void createBookWithAllDataProvided() {
        String isbn = "isbn";
        String title = "title";
        Author author = new Author("Firstname", "Lastname");

        Book testBook = new Book(isbn, title, author);

        assertThat(testBook.getIsbn()).isEqualTo(isbn);
        assertThat(testBook.getTitle()).isEqualTo(title);
        assertThat(testBook.getAuthorsFullName()).isEqualTo(author.getFullName());
    }


    @Test
    void settingSummaryOnBook() {
        Book book = new Book("isbn", "some title", new Author("first", "last"));
        String summary = "this is a summary";
        book.setSummary(summary);

        assertThat(book.getSummary()).isEqualTo("this is a summary");
    }

}