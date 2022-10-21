package com.switchfully.digitbooky.service;

import com.switchfully.digitbooky.domain.BookRepository;
import com.switchfully.digitbooky.service.dto.BookDTO;
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
