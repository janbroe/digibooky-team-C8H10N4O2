package com.switchfully.digibooky.api;


import com.switchfully.digibooky.domain.users.Feature;
import com.switchfully.digibooky.service.bookLoan.BookLoanService;
import com.switchfully.digibooky.service.bookLoan.dto.BookLoanInDTO;
import com.switchfully.digibooky.service.bookLoan.dto.BookLoanOutDTO;
import com.switchfully.digibooky.service.books.BookService;
import com.switchfully.digibooky.service.books.dto.BookDTO;
import com.switchfully.digibooky.service.books.dto.CreateBookDTO;
import com.switchfully.digibooky.service.security.SecurityService;
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
    private final SecurityService securityService;

    public BookController(BookService bookService, BookLoanService bookLoanService, SecurityService securityService) {
        this.bookService = bookService;
        this.bookLoanService = bookLoanService;
        this.securityService = securityService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<BookDTO> getAllBooks() {
        log.debug("GET -> Controller request for all books");
        return bookService.getAllBooks();
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, params = "isbn")
    @ResponseStatus(HttpStatus.OK)
    public List<BookDTO> searchBooksByIsbn(@RequestHeader String authorization, @RequestParam String isbn) {
        securityService.validateAuthorization(authorization, Feature.SEARCH_BOOK);
        log.info("GET -> Find book by isbn ".concat(isbn));
        return bookService.searchBooksByISBN(isbn);
    }


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, params = "title")
    @ResponseStatus(HttpStatus.OK)
    public List<BookDTO> searchBooksByTitle(@RequestHeader String authorization, @RequestParam String title) {
        securityService.validateAuthorization(authorization, Feature.SEARCH_BOOK);
        log.info("GET -> Find book by title ".concat(title));
        return bookService.searchBooksByTitle(title);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, params = "author")
    @ResponseStatus(HttpStatus.OK)
    public List<BookDTO> searchBooksByAuthor(@RequestHeader String authorization, @RequestParam String author) {
        securityService.validateAuthorization(authorization, Feature.SEARCH_BOOK);
        log.info("GET -> Find book by author ".concat(author));
        return bookService.searchBooksByAuthor(author);
    }
    @GetMapping(path = "{isbn}", produces = MediaType.APPLICATION_JSON_VALUE)
    public BookDTO getBookByISBN(@PathVariable String isbn) {
        log.debug("GET -> Controller request for book with ISBN ".concat(isbn));
        return bookService.getBookByISBN(isbn);
    }

    @PostMapping(value = "{userID}/{isbn}/lend", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public BookLoanOutDTO lendBook(@RequestHeader String authorization, @PathVariable String userID, @PathVariable String isbn) {
        securityService.validateAuthorization(authorization, Feature.LEND_BOOK);
        log.debug("POST -> Controller request to put book loan with userID ".concat(userID).concat(" and isbn ").concat(isbn));
        return bookLoanService.lendBook(userID, isbn);
    }

    @PostMapping(value = "{loanID}/return", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public BookLoanInDTO returnBook(@RequestHeader String authorization, @PathVariable String loanID){
        securityService.validateAuthorization(authorization, Feature.RETURN_BOOK);
        log.info("POST -> Controller request to post book return with lendingID ".concat(loanID));
        return bookLoanService.returnBook(loanID);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public BookDTO addNewBookToLibrary(@RequestHeader String authorization, @RequestBody CreateBookDTO createBookDTO) {
        securityService.validateAuthorization(authorization, Feature.CREATE_BOOK);
        log.debug("POST -> Controller request to post new book ".concat(createBookDTO.toString()));
        return bookService.createBook(createBookDTO);

    }
}
