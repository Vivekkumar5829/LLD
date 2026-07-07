package com.car.booking.strategy.fare;

import com.car.booking.model.Location;
import com.car.booking.model.enums.VehicleType;

public class SurgeFareStrategy implements FareStrategy {
    private final double surgeMultiplier;

    public SurgeFareStrategy(double surgeMultiplier) {
        if (surgeMultiplier < 1.0) {
            throw new IllegalArgumentException(
                    "Surge multiplier cannot be less than 1.0");
        }
        this.surgeMultiplier = surgeMultiplier;
    }

    @Override
    public double calculateFare(Location pickup,
                                Location drop,
                                VehicleType type) {
        // Reuse PerKm logic, add surge on top
        double baseFare = new PerKmFareStrategy()
                .calculateFare(pickup, drop, type);
        return baseFare * surgeMultiplier;
    }
}
