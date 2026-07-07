package com.booking.hotel.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class Customer {
    private final String customerId;
    private final String name;
    private final String email;
    private final String phoneNumber;
    private Address address;
    private final List<String> bookingHistory;

    public Customer(String name, String email,
                    String phoneNumber, Address address) {
        if (name == null || name.trim().isEmpty())
            throw new IllegalArgumentException(
                    "Name cannot be empty");
        if (email == null || email.trim().isEmpty())
            throw new IllegalArgumentException(
                    "Email cannot be empty");
        if (phoneNumber == null || phoneNumber.trim().isEmpty())
            throw new IllegalArgumentException(
                    "Phone cannot be empty");

        this.customerId = UUID.randomUUID().toString();
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.bookingHistory = new ArrayList<>();
    }

    public void addBookingToHistory(String bookingId) {
        bookingHistory.add(bookingId);
    }

    public void updateAddress(Address newAddress) {
        if (newAddress == null)
            throw new IllegalArgumentException(
                    "Address cannot be null");
        this.address = newAddress;
    }

    public List<String> getBookingHistory() {
        return Collections.unmodifiableList(bookingHistory);
    }

    public String getCustomerId() { return customerId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhoneNumber() { return phoneNumber; }
    public Address getAddress() { return address; }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phoneNumber + '\'' +
                '}';
    }
}