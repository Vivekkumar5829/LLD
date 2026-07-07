package com.booking.hotel.state;

import com.booking.hotel.models.Booking;

public interface IBookingState {


    void confirm(Booking booking);
    void cancel(Booking booking);
    void complete(Booking booking);

}
