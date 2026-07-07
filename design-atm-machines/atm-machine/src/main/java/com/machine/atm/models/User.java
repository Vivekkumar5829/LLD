package com.machine.atm.models;

import java.util.UUID;

public class User {
    private final String userId;
    private final String name;
    private String phone;
    private String address;
    private int age;

    public User(String name, String phone,
                String address, int age) {
        if (name == null || name.trim().isEmpty())
            throw new IllegalArgumentException(
                    "Name cannot be empty");
        if (phone == null || phone.trim().isEmpty())
            throw new IllegalArgumentException(
                    "Phone cannot be empty");

        this.userId = UUID.randomUUID().toString();
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.age = age;
    }


    public String getUserId() { return userId; }
    public String getName() { return name; }


    public String getPhone() { return phone; }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() { return address; }
    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() { return age; }
}