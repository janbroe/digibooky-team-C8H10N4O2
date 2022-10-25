package com.switchfully.digibooky.domain.exceptions;

public class WrongPasswordException extends IllegalArgumentException {
    public WrongPasswordException() {
        super("Wrong credentials");
    }
}
