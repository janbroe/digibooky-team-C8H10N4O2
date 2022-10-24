package com.switchfully.digibooky.service.dto;

import com.switchfully.digibooky.domain.users.Address;

public class MemberDTO {
    private String userId;
    private String firstname;
    private String lastname;
    private String email;
    private Address address;

    public MemberDTO setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public MemberDTO setFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public MemberDTO setLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public MemberDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public MemberDTO setAddress(Address address) {
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
