package com.booking.hotel.state;

import com.booking.hotel.enums.BookingState;
import com.booking.hotel.models.Booking;

public class PendingState implements IBookingState {

    @Override
    public void confirm(Booking booking) {
        System.out.println("Booking confirmed.");
        booking.setState(new ConfirmedState(),
                BookingState.CONFIRMED);
        booking.notifyObservers("CONFIRMED");
    }

    @Override
    public void cancel(Booking booking) {
        System.out.println("Booking cancelled. No penalty.");
        booking.setState(new CancelledState(),
                BookingState.CANCELLED);
        booking.notifyObservers("CANCELLED");
    }

    @Override
    public void complete(Booking booking) {
        throw new IllegalStateException(
                "Cannot complete a pending booking. Confirm first.");
    }
}