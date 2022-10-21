package com.switchfully.digibooky.domain.books;

public class Author {
    private final String firstname;
    private final String lastname;

    public Author(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public String getFullName() {
        return lastname.concat(", ").concat(firstname);
    }
}
