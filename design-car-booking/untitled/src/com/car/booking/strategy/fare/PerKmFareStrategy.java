package com.car.booking.strategy.fare;

import com.car.booking.model.Location;
import com.car.booking.model.enums.VehicleType;

public class PerKmFareStrategy implements FareStrategy {

    @Override
    public double calculateFare(Location pickup,
                                Location drop,
                                VehicleType type) {
        // Validate inputs
        if (pickup == null || drop == null) {
            throw new IllegalArgumentException(
                    "Pickup and drop cannot be null");
        }
        if (type == null) {
            throw new IllegalArgumentException(
                    "Vehicle type cannot be null");
        }

        // Core calculation
        double distance = pickup.distanceTo(drop);
        double ratePerKm;

        switch (type) {
            case BIKE:  ratePerKm = 8;  break;
            case AUTO:  ratePerKm = 12; break;
            case MINI:  ratePerKm = 15; break;
            case SEDAN: ratePerKm = 18; break;
            case XL:    ratePerKm = 22; break;
            default:
                throw new IllegalArgumentException(
                        "Unknown vehicle type: " + type);
        }

        return distance * ratePerKm;
    }
}
