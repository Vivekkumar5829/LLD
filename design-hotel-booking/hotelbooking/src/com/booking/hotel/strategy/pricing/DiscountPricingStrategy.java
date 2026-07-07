package com.booking.hotel.strategy.pricing;

import com.booking.hotel.models.Room;

public class DiscountPricingStrategy
        implements PricingStrategy {
    private final double discountPercent;

    public DiscountPricingStrategy(double discountPercent) {
        if (discountPercent < 0 || discountPercent > 100)
            throw new IllegalArgumentException(
                    "Discount must be between 0 and 100");
        this.discountPercent = discountPercent;
    }

    @Override
    public double calculatePrice(Room room,
                                 int numberOfNights) {
        double basePrice = room.getPricePerNight()
                * numberOfNights;
        double discount = basePrice * (discountPercent / 100);
        return basePrice - discount;
    }
}
