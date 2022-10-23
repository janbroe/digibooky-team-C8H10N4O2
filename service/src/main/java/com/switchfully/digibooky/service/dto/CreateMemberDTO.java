package com.switchfully.digibooky.service.dto;

import com.switchfully.digibooky.domain.users.Address;

public class CreateMemberDTO {
    private String inss;
    private String firstname;
    private String lastname;
    private String email;
    private Address address;

    public CreateMemberDTO  setInss(String inss) {
        this.inss = inss;
        return this;
    }


    public CreateMemberDTO  setFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }


    public CreateMemberDTO  setLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }


    public CreateMemberDTO  setEmail(String email) {
        this.email = email;
        return this;
    }


    public CreateMemberDTO  setAddress(Address address) {
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

    public String getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

}
