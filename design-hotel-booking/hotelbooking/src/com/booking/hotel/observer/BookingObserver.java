package com.booking.hotel.observer;

import com.booking.hotel.models.Booking;

public interface BookingObserver {
    void onBookingEvent(Booking booking, String event);
}