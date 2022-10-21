package com.switchfully.digibooky.service;

import com.switchfully.digibooky.domain.books.BookRepository;
import com.switchfully.digibooky.service.dto.BookDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
        this.bookMapper = new BookMapper();
    }

    public List<BookDTO> getAllBooks() {
        return bookMapper.mapBookToDTO(bookRepository.getAllBooks());
    }
}
