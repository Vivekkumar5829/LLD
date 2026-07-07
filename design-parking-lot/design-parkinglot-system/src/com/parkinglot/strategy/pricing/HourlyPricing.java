package com.parkinglot.strategy.pricing;

import com.parkinglot.enums.VehicleType;

public class HourlyPricing implements PricingStrategy{
    @Override
    public double calculateFare(VehicleType vehicleType, long hours) {
        switch (vehicleType) {
            case BIKE :
                return hours * 10;
            case CAR:
                return hours * 15;
            case TRUCK:
                return hours * 20;
            default:
                throw new IllegalArgumentException("We don't support this type");

        }
    }
}
