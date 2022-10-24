package com.switchfully.digibooky.domain.bookLoans;


import com.switchfully.digibooky.domain.books.BookRepository;
import com.switchfully.digibooky.domain.users.MemberRepository;
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
    private final MemberRepository memberRepository;

    private final Map<String, BookLoan> bookLoansByID;

    public BookLoanRepository(BookRepository bookRepository, MemberRepository memberRepository) {
        this.bookLoansByID = new HashMap<>();
        this.bookRepository = bookRepository;
        this.memberRepository = memberRepository;
    }

    public void lendOutBook(BookLoan bookLoan){
        if (!memberRepository.doesMemberExist(bookLoan.getMemberID())) {
            throw new NoSuchElementException("Member with ID ".concat(bookLoan.getMemberID()).concat(" does not exist"));
        }
        if (!isBookAvailable(bookLoan.getBookISBN())){
            throw new NoSuchElementException("Book with ISBN ".concat(bookLoan.getBookISBN()).concat(" is not available."));
        }
        bookLoansByID.put(bookLoan.getMemberID(), bookLoan);
        bookRepository.getBookByISBN(bookLoan.getBookISBN()).setUnavailable();
        log.info("POST -> ".concat(bookLoan.toString()));
    }

    public boolean isBookAvailable(String isbn) {
        return bookRepository.getBookByISBN(isbn).isAvailable();
    }

}
