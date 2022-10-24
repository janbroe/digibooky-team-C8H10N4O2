package com.switchfully.digibooky.service.bookLoan;

import com.switchfully.digibooky.domain.bookLoans.BookLoan;
import com.switchfully.digibooky.service.bookLoan.dto.BookLoanDTO;

public class BookLoanMapper {

    public BookLoanDTO mapToDTO(BookLoan bookLoan) {
        return new BookLoanDTO()
                .setLoanID(bookLoan.getLoanID())
                .setBookISBN(bookLoan.getBookISBN())
                .setMemberID(bookLoan.getMemberID())
                .setLoanDate(bookLoan.getLoanDate())
                .setDueDate(bookLoan.getDueDate());
    }
}
