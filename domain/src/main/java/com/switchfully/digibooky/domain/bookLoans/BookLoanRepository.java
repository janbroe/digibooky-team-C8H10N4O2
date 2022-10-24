package com.switchfully.digibooky.domain.bookLoans;


import com.switchfully.digibooky.domain.books.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@Repository
public class BookLoanRepository {

    @Autowired
    private final BookRepository bookRepository;

    private final Map<String, BookLoan> bookLoansByID;

    public BookLoanRepository(BookRepository bookRepository) {
        this.bookLoansByID = new HashMap<>();
        this.bookRepository = bookRepository;
    }

    public void lendOutBook(BookLoan bookLoan){
        if (!isBookAvailable(bookLoan.getBookISBN())){
            throw new NoSuchElementException("Book with ISBN ".concat(bookLoan.getBookISBN()).concat(" is not available."));
        }
        bookLoansByID.put(bookLoan.getMemberID(), bookLoan);
    }

    public boolean isBookAvailable(String isbn) {
        return bookRepository.getBookByISBN(isbn).isAvailable();
    }

}
