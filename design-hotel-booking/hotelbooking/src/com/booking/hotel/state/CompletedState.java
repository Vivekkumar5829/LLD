package com.booking.hotel.state;

import com.booking.hotel.models.Booking;

public class CompletedState implements IBookingState {

    @Override
    public void confirm(Booking booking) {
        throw new IllegalStateException(
                "Cannot confirm a completed booking.");
    }

    @Override
    public void cancel(Booking booking) {
        throw new IllegalStateException(
                "Cannot cancel a completed booking.");
    }

    @Override
    public void complete(Booking booking) {
        throw new IllegalStateException(
                "Booking is already completed.");
    }
}