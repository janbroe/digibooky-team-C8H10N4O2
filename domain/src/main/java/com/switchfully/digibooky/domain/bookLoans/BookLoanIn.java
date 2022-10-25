package com.switchfully.digibooky.domain.bookLoans;

import java.time.LocalDate;

public class BookLoanIn extends BookLoan {
    private final LocalDate returnDate;

    private String message;
    private double fine;
    public BookLoanIn(String memberID, String bookISBN) {
        super(memberID, bookISBN);
        returnDate = LocalDate.now();
        message = "";
        fine = 0;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setFine(double fine) {
        this.fine = fine;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public double getFine() {
        return fine;
    }

    public String getMessage() {
        return message;
    }

    public void checkBookLate(LocalDate dueDate) {
        if (LocalDate.now().isAfter(dueDate)) {
            setMessage("You are late");
        } else {
            setMessage("Thank you for being a responsible adult");
        }
    }
}
