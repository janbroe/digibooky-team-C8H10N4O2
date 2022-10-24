package com.switchfully.digibooky.service.books;

import com.switchfully.digibooky.domain.books.Book;
import com.switchfully.digibooky.service.books.dto.BookDTO;

import java.util.Collection;
import java.util.List;

public class BookMapper {
    public BookDTO mapBookToDTO(Book book) {
        return new BookDTO()
                .setIsbn(book.getIsbn())
                .setTitle(book.getTitle())
                .setAuthor(book.getAuthorsFullName())
                .setSummary(book.getSummary());
    }

    public List<BookDTO> mapBookToDTO(Collection<Book> books) {
        return books.stream()
                .map(this::mapBookToDTO)
                .toList();
    }

}
