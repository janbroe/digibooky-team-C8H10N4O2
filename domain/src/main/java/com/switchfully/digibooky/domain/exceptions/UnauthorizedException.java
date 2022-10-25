package com.switchfully.digibooky.domain.exceptions;

public class UnauthorizedException extends IllegalArgumentException {
    public UnauthorizedException() {
        super("User does not have authorized access");
    }
}
