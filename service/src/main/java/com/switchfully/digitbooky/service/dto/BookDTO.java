package com.switchfully.digitbooky.service.dto;

import com.switchfully.digitbooky.domain.Author;

import java.util.Objects;

public class BookDTO {
    private String isbn;
    private String title;
    private String author;

    public BookDTO setIsbn(String isbn) {
        this.isbn = isbn;
        return this;
    }

    public BookDTO setTitle(String title) {
        this.title = title;
        return this;
    }

    public BookDTO setAuthor(String author) {
        this.author = author;
        return this;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookDTO bookDTO = (BookDTO) o;
        return Objects.equals(isbn, bookDTO.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn);
    }
}
