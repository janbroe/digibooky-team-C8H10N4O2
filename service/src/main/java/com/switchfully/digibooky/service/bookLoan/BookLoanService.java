package com.switchfully.digibooky.service.bookLoan;

import com.switchfully.digibooky.domain.bookLoans.BookLoanIn;
import com.switchfully.digibooky.domain.bookLoans.BookLoanOut;
import com.switchfully.digibooky.domain.bookLoans.BookLoanRepository;
import com.switchfully.digibooky.service.bookLoan.dto.BookLoanInDTO;
import com.switchfully.digibooky.service.bookLoan.dto.BookLoanOutDTO;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookLoanService {

    private final BookLoanRepository bookLoanRepository;
    private final BookLoanMapper bookLoanMapper;

    public BookLoanService(BookLoanRepository bookLoanRepository) {
        this.bookLoanRepository = bookLoanRepository;
        bookLoanMapper = new BookLoanMapper();
    }

    public BookLoanOutDTO lendBook(String userID, String isbn) {
        BookLoanOut bookLoanOut = new BookLoanOut(userID, isbn);
        bookLoanRepository.lendOutBook(bookLoanOut);
        return bookLoanMapper.mapBookLoanOutToDTO(bookLoanOut);
    }

    public BookLoanInDTO returnBook(String loanID) {
        BookLoanOut bookLoanOut = bookLoanRepository.getBookLoanOutByLoanID(loanID);
        BookLoanIn bookLoanIn = bookLoanMapper.mapBookLoanOutToBookLoanIn(bookLoanOut);
        bookLoanIn.checkBookLate(bookLoanOut.getDueDate());
        bookLoanRepository.returnBook(bookLoanIn);
        return bookLoanMapper.mapBookLoanInToDTO(bookLoanIn);
    }
}
