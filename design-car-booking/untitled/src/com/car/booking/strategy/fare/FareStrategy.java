package com.car.booking.strategy.fare;

import com.car.booking.model.Location;
import com.car.booking.model.enums.VehicleType;

public interface FareStrategy {
    double calculateFare(Location pickup, Location drop, VehicleType type);
}
