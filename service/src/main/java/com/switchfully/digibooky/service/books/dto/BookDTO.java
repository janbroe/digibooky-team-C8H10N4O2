package com.switchfully.digibooky.service.books.dto;

import java.util.Objects;

public class BookDTO {
    private String isbn;
    private String title;
    private String author;
    private String summary;

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

    public BookDTO setSummary(String summary) {
        this.summary = summary;
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

    public String getSummary() {
        return summary;
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
