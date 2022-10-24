package com.switchfully.digibooky.domain.bookLoans;

import java.time.LocalDate;
import java.util.UUID;

public class BookLoan {

    public static final int DEFAULT_LENDING_TIME_IN_DAYS = 21;
    private final String memberID;
    private final String bookISBN;
    private final LocalDate loanDate;
    private final LocalDate dueDate;
    private final String loanID;

    public BookLoan(String memberID, String bookISBN) {
        this.loanID = UUID.randomUUID().toString();
        this.memberID = memberID;
        this.bookISBN = bookISBN;
        this.loanDate = LocalDate.now();
        this.dueDate = loanDate.plusDays(DEFAULT_LENDING_TIME_IN_DAYS);
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

    public String getLoanID() {
        return loanID;
    }

    @Override
    public String toString() {
        return "BookLoan{" +
                "memberID='" + memberID + '\'' +
                ", bookISBN='" + bookISBN + '\'' +
                ", loanDate=" + loanDate +
                ", dueDate=" + dueDate +
                ", loanID='" + loanID + '\'' +
                '}';
    }
}
