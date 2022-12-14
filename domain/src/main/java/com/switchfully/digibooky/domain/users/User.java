package com.switchfully.digibooky.domain.users;

import com.switchfully.digibooky.domain.PasswordHasher;

import java.util.UUID;
import java.util.regex.Pattern;

public class User {
    public static final int INSS_LENGTH = 1;
    private final String userId;
    private final String inss;
    private final String firstname;
    private final String lastname;
    private final String email;
    private final Address address;
    private final String password;
    private final Role role;

    public User(String inss, String firstname, String lastname, String password, String email, Address address, Role role) {
        this.userId = UUID.randomUUID().toString();
        this.inss = validateINSS(inss);
        this.firstname = firstname;
        this.lastname = lastnameNotNull(lastname);
        this.password = new PasswordHasher(password).getHashedPassword();
        this.email = emailVerification(email);
        this.address = address;
        this.role = role;
    }

    public User(String inss, String lastname, String password, String email, Address address, Role role) {
        this(inss, null, lastname, password, email, address, role);
    }

    public String validateINSS(String inss) {
        if (inss == null || inss.length() < INSS_LENGTH) {
            throw new IllegalArgumentException("INSS should not be null and consists of " + INSS_LENGTH + " characters");
        }
        return inss;
    }

    public String emailVerification(String emailToVerify) {
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

    public boolean doesPasswordMatch(String passwordToMatch) {
        return password.equals(new PasswordHasher(passwordToMatch).getHashedPassword());
    }

    public String getPassword() {
        return password;
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

    public boolean canHaveAccessTo(Feature feature) {
        return role.containsFeature(feature);
    }

    @Override
    public String toString() {
        return role.toString() +
                "{userId='" + userId + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
