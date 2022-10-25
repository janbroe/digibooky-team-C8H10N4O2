package com.switchfully.digibooky.api;


import com.switchfully.digibooky.service.bookLoan.BookLoanService;
import com.switchfully.digibooky.service.bookLoan.dto.BookLoanInDTO;
import com.switchfully.digibooky.service.bookLoan.dto.BookLoanOutDTO;
import com.switchfully.digibooky.service.books.BookService;
import com.switchfully.digibooky.service.books.dto.BookDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/books")
@CrossOrigin
public class BookController {

    private final Logger log = LoggerFactory.getLogger(BookController.class);
    private final BookService bookService;
    private final BookLoanService bookLoanService;

    public BookController(BookService bookService, BookLoanService bookLoanService) {
        this.bookService = bookService;
        this.bookLoanService = bookLoanService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<BookDTO> getAllBooks() {
        log.debug("GET -> Controller request for all books");
        return bookService.getAllBooks();
    }

    @GetMapping(path = "{isbn}", produces = MediaType.APPLICATION_JSON_VALUE)
    public BookDTO getBookByISBN(@PathVariable String isbn) {
        log.debug("GET -> Controller request for book with ISBN ".concat(isbn));
        return bookService.getBookByISBN(isbn);
    }

    @PostMapping(value = "{userID}/{isbn}/lend", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public BookLoanOutDTO lendBook(@PathVariable String userID, @PathVariable String isbn) {
        log.debug("POST -> Controller request to put book loan with userID ".concat(userID).concat(" and isbn ").concat(isbn));
        return bookLoanService.lendBook(userID, isbn);
    }

    @PostMapping(value = "{loanID}/return", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public BookLoanInDTO returnBook(@PathVariable String loanID){
        log.debug("POST -> Controller request to post book return with lendingID ".concat(loanID));
        return bookLoanService.returnBook(loanID);
    }

}
