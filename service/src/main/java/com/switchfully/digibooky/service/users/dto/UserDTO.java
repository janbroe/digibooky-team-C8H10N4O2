package com.switchfully.digibooky.service.users.dto;

import com.switchfully.digibooky.domain.users.Address;

public class UserDTO {
    private String userId;
    private String firstname;
    private String lastname;
    private String email;
    private Address address;

    public UserDTO setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public UserDTO setFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public UserDTO setLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public UserDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserDTO setAddress(Address address) {
        this.address = address;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }
}
