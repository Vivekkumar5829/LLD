package com.booking.hotel.models;

public class Address {
    private final String street;
    private final String city;
    private final String state;
    private final String country;
    private final String pincode;

    public Address(String street, String city,
                   String state, String country,
                   String pincode) {
        if (street == null || street.trim().isEmpty())
            throw new IllegalArgumentException(
                    "Street cannot be empty");
        if (city == null || city.trim().isEmpty())
            throw new IllegalArgumentException(
                    "City cannot be empty");
        if (country == null || country.trim().isEmpty())
            throw new IllegalArgumentException(
                    "Country cannot be empty");
        if (pincode == null || pincode.trim().isEmpty())
            throw new IllegalArgumentException(
                    "Pincode cannot be empty");

        this.street = street;
        this.city = city;
        this.state = state;
        this.country = country;
        this.pincode = pincode;
    }

    public String getStreet() { return street; }
    public String getCity() { return city; }
    public String getState() { return state; }
    public String getCountry() { return country; }
    public String getPincode() { return pincode; }

    @Override
    public String toString() {
        return street + ", " + city +
                (state != null ? ", " + state : "") +
                " - " + pincode +
                ", " + country;
    }
}