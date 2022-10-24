package com.switchfully.digibooky.service.bookLoan;

import com.switchfully.digibooky.domain.bookLoans.BookLoan;
import com.switchfully.digibooky.domain.bookLoans.BookLoanRepository;
import com.switchfully.digibooky.service.bookLoan.dto.BookLoanDTO;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookLoanService {

    private final BookLoanRepository bookLoanRepository;
    private final BookLoanMapper bookLoanMapper;

    public BookLoanService(BookLoanRepository bookLoanRepository) {
        this.bookLoanRepository = bookLoanRepository;
        bookLoanMapper = new BookLoanMapper();
    }

    public BookLoanDTO lendBook(String userID, String isbn) {
        BookLoan bookLoan = new BookLoan(userID, isbn);
        bookLoanRepository.lendOutBook(bookLoan);
        return bookLoanMapper.mapToDTO(bookLoan);
    }
}
