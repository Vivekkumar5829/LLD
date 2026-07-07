package com.booking.hotel.state;

import com.booking.hotel.enums.BookingState;
import com.booking.hotel.models.Booking;

public class ConfirmedState implements IBookingState {

    @Override
    public void confirm(Booking booking) {
        throw new IllegalStateException(
                "Booking is already confirmed.");
    }

    @Override
    public void cancel(Booking booking) {
        // ✅ Allowed — but penalty applies
        System.out.println("Booking cancelled. " +
                "Cancellation penalty applies.");
        booking.setState(new CancelledState(),
                BookingState.CANCELLED);
        booking.notifyObservers("CANCELLED");
    }

    @Override
    public void complete(Booking booking) {
        System.out.println("Booking completed. Visit again!");
        booking.setState(new CompletedState(),
                BookingState.COMPLETED);
        booking.notifyObservers("COMPLETED");
    }
}