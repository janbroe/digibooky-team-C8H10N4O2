package com.switchfully.digibooky.domain.bookLoans;


import com.switchfully.digibooky.domain.books.BookRepository;
import com.switchfully.digibooky.domain.users.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@Repository
public class BookLoanRepository {

    private final Logger log = LoggerFactory.getLogger(BookLoanRepository.class);
    @Autowired
    private final BookRepository bookRepository;
    @Autowired
    private final UserRepository userRepository;

    private final Map<String, BookLoan> bookLoansByID;

    public BookLoanRepository(BookRepository bookRepository, UserRepository userRepository) {
        this.bookLoansByID = new HashMap<>();
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    public void lendOutBook(BookLoanOut bookLoanOut) {
        if (!userRepository.doesMemberExist(bookLoanOut.getMemberID())) {
            throw new NoSuchElementException("Member with ID ".concat(bookLoanOut.getMemberID()).concat(" does not exist"));
        }
        if (!isBookAvailable(bookLoanOut.getBookISBN())) {
            throw new NoSuchElementException("Book with ISBN ".concat(bookLoanOut.getBookISBN()).concat(" is not available."));
        }
        bookLoansByID.put(bookLoanOut.getLoanID(), bookLoanOut);
        bookRepository.getBookByISBN(bookLoanOut.getBookISBN()).setUnavailable();
        log.info("POST -> ".concat(bookLoanOut.toString()));
    }

    public boolean isBookAvailable(String isbn) {
        return bookRepository.getBookByISBN(isbn).isAvailable();
    }

    public BookLoanOut getBookLoanOutByLoanID(String loanID) {
        if (!bookLoansByID.containsKey(loanID)) {
            throw new NoSuchElementException("Book loan with ID ".concat(loanID).concat(" could not be found"));
        }
        return (BookLoanOut) bookLoansByID.get(loanID);
    }

    public void returnBook(BookLoanIn bookLoanIn) {
        bookLoansByID.put(bookLoanIn.getLoanID(), bookLoanIn);
        bookRepository.getBookByISBN(bookLoanIn.getBookISBN()).setAvailable();
    }
}
