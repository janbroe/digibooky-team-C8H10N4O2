package com.switchfully.digibooky.service.users.dto;

import com.switchfully.digibooky.domain.users.Address;

public class CreateUserDTO {
    private String inss;
    private String firstname;
    private String lastname;
    private String password;
    private String email;
    private Address address;

    public CreateUserDTO setInss(String inss) {
        this.inss = inss;
        return this;
    }


    public CreateUserDTO setFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }


    public CreateUserDTO setLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public CreateUserDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    public CreateUserDTO setEmail(String email) {
        this.email = email;
        return this;
    }


    public CreateUserDTO setAddress(Address address) {
        this.address = address;
        return this;
    }

    public String getInss() {
        return inss;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

}
