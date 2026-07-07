package com.car.booking.strategy.matching;

import com.car.booking.model.Driver;
import com.car.booking.model.Location;
import com.car.booking.model.enums.VehicleType;

import java.util.List;

public class RatingBasedStrategy implements DriverMatchingStrategy {

    @Override
    public Driver findDriver(List<Driver> drivers,
                             Location pickup,
                             VehicleType type) {

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

        Driver bestDriver = null;
        double bestRating = -1;
        double minDistance = Double.MAX_VALUE;

        for (Driver driver : drivers) {
            if (!driver.isAvailable()) continue;
            if (driver.getVehicle().getVehicleType() != type) continue;

            double rating = driver.getRating();
            double distance = driver.getLocation().distanceTo(pickup);


            if (rating > bestRating) {
                bestRating = rating;
                minDistance = distance;
                bestDriver = driver;
            }

            else if (rating == bestRating && distance < minDistance) {
                minDistance = distance;
                bestDriver = driver;
            }
        }
        return bestDriver;
    }
}
