package com.switchfully.digibooky.service.bookLoan.dto;

import java.time.LocalDate;

public class BookLoanDTO {
    private String loanID;
    private String memberID;
    private String bookISBN;
    private LocalDate loanDate;
    private LocalDate dueDate;

    public BookLoanDTO setLoanID(String loanID) {
        this.loanID = loanID;
        return this;
    }
    public BookLoanDTO setMemberID(String memberID) {
        this.memberID = memberID;
        return this;
    }
    public BookLoanDTO setBookISBN(String bookISBN) {
        this.bookISBN = bookISBN;
        return this;
    }
    public BookLoanDTO setLoanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
        return this;
    }
    public BookLoanDTO setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
        return this;
    }

    public String getLoanID() {
        return loanID;
    }

    public String getMemberID() {
        return memberID;
    }

    public String getBookISBN() {
        return bookISBN;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }
}
