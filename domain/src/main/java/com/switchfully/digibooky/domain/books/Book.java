package com.switchfully.digibooky.domain.books;

public class Book {
    private final String isbn;
    private final String title;
    private final Author author;
    private String summary;

    public Book(String isbn, String title, Author author) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        summary = null;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthorsFullName() {

        return author.getFullName();
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
