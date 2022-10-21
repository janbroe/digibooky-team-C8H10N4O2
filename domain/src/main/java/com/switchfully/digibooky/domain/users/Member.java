package com.switchfully.digibooky.domain.users;

import java.util.UUID;
import java.util.regex.Pattern;

public class Member {
    private final String userId;
    private final String inss;
    private final String firstname;
    private final String lastname;
    private final String email;
    private final Address address;

    public Member(String inss, String firstname, String lastname, String email, Address address) {
        this.userId = UUID.randomUUID().toString();
        this.inss = inss;
        this.firstname = firstname;
        this.lastname = lastnameNotNull(lastname);
        this.email = emailVerification(email);
        this.address = address;
    }

    public Member(String inss, String lastname, String email, Address address) {
        this(inss, null, lastname, email, address);
    }

    public String emailVerification(String emailToVerify) {
        //todo
        if (emailToVerify == null) {
            throw new IllegalArgumentException("No email address was given.");
        }
        if (!isEmailFormat(emailToVerify)) {
            throw new IllegalArgumentException("Wrong email format. Please review your input.");
        }
        return emailToVerify;
    }

    public boolean isEmailFormat(String email) {
        return Pattern.matches("\\b[A-Z0-9._%-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}\\b", email.toUpperCase());
    }

    public String lastnameNotNull(String lastname){
        if (lastname == null) {
            throw new IllegalArgumentException("Please provide a lastname.");
        }
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public String getLastname() {
        return lastname;
    }

    public Address getAddress() {
        return address;
    }

    public String getInss() {
        return inss;
    }

    public String getUserId() {
        return userId;
    }

    public String getFirstname() {
        return firstname;
    }
}
