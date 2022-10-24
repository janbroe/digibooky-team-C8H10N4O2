package com.switchfully.digibooky.domain.users;

import java.util.Objects;

public class Address {
    private final String streetname;
    private final String streetnumber;
    private final String postalcode;
    private final String city;

    public Address(String streetname, String streetnumber, String postalcode, String city) {
        this.streetname = streetname;
        this.streetnumber = streetnumber;
        this.postalcode = postalcode;
        this.city = cityVerification(city);
    }

    public Address(String city) {
        this(null, null, null, city);
    }

    public String cityVerification(String city){
        if (city == null) {
            throw new IllegalArgumentException("Please provide a valid input for city.");
        }
        return city;
    }

    public String getCity() {
        return city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address address)) return false;
        return Objects.equals(streetname, address.streetname) && Objects.equals(streetnumber, address.streetnumber) && Objects.equals(postalcode, address.postalcode) && Objects.equals(getCity(), address.getCity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(streetname, streetnumber, postalcode, getCity());
    }
}
