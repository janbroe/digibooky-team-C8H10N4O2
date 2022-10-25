package com.switchfully.digibooky.domain.bookLoans;

import java.time.LocalDate;
import java.util.UUID;

public class BookLoanOut extends BookLoan {
    private final LocalDate loanDate;
    private final LocalDate dueDate;

    public BookLoanOut(String memberID, String bookISBN) {
        super(memberID, bookISBN);
        this.loanDate = LocalDate.now();
        this.dueDate = loanDate.plusDays(DEFAULT_LENDING_TIME_IN_DAYS);
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }
}
