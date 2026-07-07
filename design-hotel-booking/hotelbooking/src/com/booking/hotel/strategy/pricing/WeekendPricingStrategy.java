package com.booking.hotel.strategy.pricing;

import com.booking.hotel.models.Room;

public class WeekendPricingStrategy implements PricingStrategy{
    @Override
    public double calculatePrice(Room room, int numberOfNights) {
        return room.getPricePerNight() * numberOfNights * 1.7;
    }
}
