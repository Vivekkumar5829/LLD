package com.booking.hotel.strategy.pricing;

import com.booking.hotel.models.Room;

public interface PricingStrategy {
    double calculatePrice(Room room , int numberOfNights);
}
