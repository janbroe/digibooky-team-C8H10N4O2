package com.switchfully.digibooky.service;

import com.switchfully.digibooky.domain.bookLoans.BookLoanRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookLoanService {

    private final BookLoanRepository bookLoanRepository;

    public BookLoanService(BookLoanRepository bookLoanRepository) {
        this.bookLoanRepository = bookLoanRepository;
    }
}
