package com.parkinglot.strategy.pricing;

import com.parkinglot.enums.VehicleType;

public class WeekendPricing implements PricingStrategy{
    @Override
    public double calculateFare(VehicleType vehicleType, long hours) {
        double base = new HourlyPricing().calculateFare(vehicleType, hours);
        return base * 1.5;
    }
}
