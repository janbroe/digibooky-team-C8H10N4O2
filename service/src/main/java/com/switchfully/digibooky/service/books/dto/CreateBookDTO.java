package com.switchfully.digibooky.service.books.dto;

import com.switchfully.digibooky.domain.books.Author;

public class CreateBookDTO {

    private String isbn;
    private String title;
    private Author author;

    public CreateBookDTO setIsbn(String isbn) {
        this.isbn = isbn;
        return this;
    }

    public CreateBookDTO setTitle(String title) {
        this.title = title;
        return this;
    }

    public CreateBookDTO setAuthor(Author author) {
        this.author = author;
        return this;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public Author getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return "CreateBookDTO{" +
                "isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", author=" + author +
                '}';
    }
}

