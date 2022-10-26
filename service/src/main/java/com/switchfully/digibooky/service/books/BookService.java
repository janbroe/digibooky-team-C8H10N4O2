package com.switchfully.digibooky.service.books;

import com.switchfully.digibooky.domain.books.Book;
import com.switchfully.digibooky.domain.books.BookRepository;
import com.switchfully.digibooky.service.books.dto.BookDTO;
import com.switchfully.digibooky.service.books.dto.CreateBookDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public BookDTO getBookByISBN(String isbn) {
        return bookMapper.mapBookToDTO(bookRepository.getBookByISBN(isbn));
    }

    public BookDTO createBook(CreateBookDTO createBookDTO) {
        Book newBook = new Book(createBookDTO.getIsbn(), createBookDTO.getTitle(), createBookDTO.getAuthor());
        bookRepository.saveBook(newBook);
        return bookMapper.mapBookToDTO(newBook);
    }

    public List<BookDTO> searchBooksByISBN(String isbn) {
        return bookRepository.searchAllBooksByISBN(isbn).stream()
                .map(bookMapper::mapBookToDTO)
                .toList();
    }

    public List<BookDTO> searchBooksByTitle(String title) {
        return bookRepository.searchAllBooksByTitle(title).stream()
                .map(bookMapper::mapBookToDTO)
                .toList();
    }

    public List<BookDTO> searchBooksByAuthor(String author) {
        return bookRepository.searchAllBooksByAuthor(author).stream()
                .map(bookMapper::mapBookToDTO)
                .toList();
    }
}
