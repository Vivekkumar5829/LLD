package com.parkinglot.strategy.pricing;

import com.parkinglot.enums.VehicleType;

public interface PricingStrategy {
    double calculateFare(VehicleType vehicleType, long hours);
}
