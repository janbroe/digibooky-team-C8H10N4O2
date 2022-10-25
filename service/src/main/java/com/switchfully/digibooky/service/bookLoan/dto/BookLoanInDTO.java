package com.switchfully.digibooky.service.bookLoan.dto;

import java.time.LocalDate;

public class BookLoanInDTO {
    private String loanID;
    private String memberID;
    private String bookISBN;

    public LocalDate returnDate;

    public String message;

    public double fee;

    public BookLoanInDTO setLoanID(String loanID) {
        this.loanID = loanID;
        return this;
    }

    public BookLoanInDTO setMemberID(String memberID) {
        this.memberID = memberID;
        return this;
    }

    public BookLoanInDTO setBookISBN(String bookISBN) {
        this.bookISBN = bookISBN;
        return this;
    }

    public BookLoanInDTO setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
        return this;
    }

    public BookLoanInDTO setMessage(String message) {
        this.message = message;
        return this;
    }

    public BookLoanInDTO setFine(double fee) {
        this.fee = fee;
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

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public String getMessage() {
        return message;
    }

    public double getFee() {
        return fee;
    }
}
