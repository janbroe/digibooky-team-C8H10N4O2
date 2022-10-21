package com.switchfully.digibooky.domain.users;

public class Address {
    private final String streetname;
    private final String streetnumber;
    private final String postalcode;
    private final String city;

    public Address(String streetname, String streetnumber, String postalcode, String city) {
        this.streetname = streetname;
        this.streetnumber = streetnumber;
        this.postalcode = postalcode;
        this.city = city;
    }

    public Address(String city) {
        this(null, null, null, city);
    }
}
