package com.car.booking.model;

import com.car.booking.model.enums.DriverState;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class Driver {
    private final String driverId;
    private final String name;
    private final String phone;
    private Vehicle vehicle;
    private Location location;
    private DriverState driverState;
    private double rating;
    private final List<String> rideHistory;

    public Driver(String name, String phone,
                  Vehicle vehicle, Location location) {
        // Validate
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        if (phone == null || phone.trim().isEmpty()) {
            throw new IllegalArgumentException("Phone cannot be empty");
        }
        if (vehicle == null) {
            throw new IllegalArgumentException("Vehicle cannot be null");
        }
        if (location == null) {
            throw new IllegalArgumentException("Location cannot be null");
        }

        this.driverId = UUID.randomUUID().toString();
        this.name = name;
        this.phone = phone;
        this.vehicle = vehicle;
        this.location = location;
        this.driverState = DriverState.AVAILABLE; // starts available
        this.rating = 5.0;                        // starts at max rating
        this.rideHistory = new ArrayList<>();
    }

    // Called by RideController when ride is assigned
    public void updateState(DriverState newState) {
        if (newState == null) {
            throw new IllegalArgumentException("State cannot be null");
        }
        this.driverState = newState;
    }

    // Called constantly as driver moves
    public void updateLocation(Location newLocation) {
        if (newLocation == null) {
            throw new IllegalArgumentException("Location cannot be null");
        }
        this.location = newLocation;
    }

    // Called after ride completes
    public void addRideToHistory(String rideId) {
        rideHistory.add(rideId);
    }

    // Called after rider rates the driver
    public void updateRating(double newRating) {
        if (newRating < 1.0 || newRating > 5.0) {
            throw new IllegalArgumentException(
                    "Rating must be between 1.0 and 5.0");
        }
        // Simple average — production would be weighted
        this.rating = (this.rating + newRating) / 2.0;
    }

    public boolean isAvailable() {
        return driverState == DriverState.AVAILABLE;
    }

    public List<String> getRideHistory() {
        return Collections.unmodifiableList(rideHistory);
    }

    public String getDriverId() { return driverId; }
    public String getName() { return name; }
    public String getPhone() { return phone; }
    public Vehicle getVehicle() { return vehicle; }
    public Location getLocation() { return location; }
    public DriverState getDriverState() { return driverState; }
    public double getRating() { return rating; }
}