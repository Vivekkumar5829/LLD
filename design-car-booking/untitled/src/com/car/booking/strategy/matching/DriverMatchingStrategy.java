package com.car.booking.strategy.matching;

import com.car.booking.model.Driver;
import com.car.booking.model.Location;
import com.car.booking.model.enums.VehicleType;

import java.util.List;

public interface DriverMatchingStrategy {
    Driver findDriver(List<Driver> drivers, Location pickup, VehicleType type);
}
