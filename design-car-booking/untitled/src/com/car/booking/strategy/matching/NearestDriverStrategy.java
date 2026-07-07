package com.car.booking.strategy.matching;

import com.car.booking.model.Driver;
import com.car.booking.model.Location;
import com.car.booking.model.enums.DriverState;
import com.car.booking.model.enums.VehicleType;

import java.util.List;

public class NearestDriverStrategy implements DriverMatchingStrategy {

    @Override
    public Driver findDriver(List<Driver> drivers,
                             Location pickup,
                             VehicleType type) {
        // Validate
        if (drivers == null || drivers.isEmpty()) {
            throw new IllegalArgumentException(
                    "Driver list cannot be null or empty");
        }
        if (pickup == null) {
            throw new IllegalArgumentException(
                    "Pickup location cannot be null");
        }
        if (type == null) {
            throw new IllegalArgumentException(
                    "Vehicle type cannot be null");
        }

        Driver nearestDriver = null;
        double minDistance = Double.MAX_VALUE;

        for (Driver driver : drivers) {
            // Use isAvailable() — Tell Don't Ask
            if (!driver.isAvailable()) continue;

            if (driver.getVehicle().getVehicleType() != type) continue;

            double distance = driver.getLocation().distanceTo(pickup);

            if (distance < minDistance) {
                minDistance = distance;
                nearestDriver = driver;
            }
        }
        return nearestDriver;
    }
}
