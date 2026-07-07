package com.car.booking.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class Rider {
    private final String riderId;
    private final String name;
    private final String phone;
    private Location location;
    private final List<String> rideHistory;

    public Rider(String name, String phone, Location location) {

        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        if (phone == null || phone.trim().isEmpty()) {
            throw new IllegalArgumentException("Phone cannot be empty");
        }
        if (location == null) {
            throw new IllegalArgumentException("Location cannot be null");
        }

        this.riderId = UUID.randomUUID().toString();
        this.name = name;
        this.phone = phone;
        this.location = location;
        this.rideHistory = new ArrayList<>();
    }


    public void updateLocation(Location newLocation) {
        if (newLocation == null) {
            throw new IllegalArgumentException("Location cannot be null");
        }
        this.location = newLocation;
    }


    public void addRideToHistory(String rideId) {
        rideHistory.add(rideId);
    }


    public List<String> getRideHistory() {
        return Collections.unmodifiableList(rideHistory);
    }

    public String getRiderId() { return riderId; }
    public String getName() { return name; }
    public String getPhone() { return phone; }
    public Location getLocation() { return location; }
}