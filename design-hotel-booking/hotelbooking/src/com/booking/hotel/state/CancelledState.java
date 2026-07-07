package com.booking.hotel.state;

import com.booking.hotel.models.Booking;

public class CancelledState implements IBookingState{

    @Override
    public void confirm(Booking booking) {
        throw new IllegalStateException("Cannot confirm cancelled booking");
    }

    @Override
    public void cancel(Booking booking) {
        throw new IllegalStateException("Cannot recancel the cancelled booking");
    }

    @Override
    public void complete(Booking booking) {
        throw new IllegalStateException("Cannot complete cancelled booking");
    }
}
