package com.switchfully.digibooky.domain.bookLoans;

import java.time.LocalDate;
import java.util.UUID;

public abstract class BookLoan {

    public static final int DEFAULT_LENDING_TIME_IN_DAYS = 21;

    private final String loanID;
    private final String memberID;
    private final String bookISBN;


    public BookLoan(String memberID, String bookISBN) {
        this.loanID = UUID.randomUUID().toString();
        this.memberID = memberID;
        this.bookISBN = bookISBN;
    }

    public String getMemberID() {
        return memberID;
    }

    public String getBookISBN() {
        return bookISBN;
    }

    public String getLoanID() {
        return loanID;
    }

    @Override
    public String toString() {
        return "BookLoan{" +
                "memberID='" + memberID + '\'' +
                ", bookISBN='" + bookISBN + '\'' +
                ", loanID='" + loanID + '\'' +
                '}';
    }
}
