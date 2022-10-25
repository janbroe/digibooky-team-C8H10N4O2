package com.switchfully.digibooky.service.bookLoan;

import com.switchfully.digibooky.domain.bookLoans.BookLoanIn;
import com.switchfully.digibooky.domain.bookLoans.BookLoanOut;
import com.switchfully.digibooky.service.bookLoan.dto.BookLoanInDTO;
import com.switchfully.digibooky.service.bookLoan.dto.BookLoanOutDTO;

public class BookLoanMapper {

    public BookLoanOutDTO mapBookLoanOutToDTO(BookLoanOut bookLoanOut) {
        return new BookLoanOutDTO()
                .setLoanID(bookLoanOut.getLoanID())
                .setBookISBN(bookLoanOut.getBookISBN())
                .setMemberID(bookLoanOut.getMemberID())
                .setLoanDate(bookLoanOut.getLoanDate())
                .setDueDate(bookLoanOut.getDueDate());
    }

    public BookLoanInDTO mapBookLoanInToDTO(BookLoanIn bookLoanIn) {
        return new BookLoanInDTO()
                .setLoanID(bookLoanIn.getLoanID())
                .setBookISBN(bookLoanIn.getBookISBN())
                .setMemberID(bookLoanIn.getMemberID())
                .setReturnDate(bookLoanIn.getReturnDate())
                .setMessage(bookLoanIn.getMessage())
                .setFine(bookLoanIn.getFine());
    }

    public BookLoanIn mapBookLoanOutToBookLoanIn(BookLoanOut bookLoanOut) {
        return new BookLoanIn(bookLoanOut.getMemberID(), bookLoanOut.getBookISBN());
    }
}
