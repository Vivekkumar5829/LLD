package com.booking.hotel.service;

import com.booking.hotel.models.Booking;
import com.booking.hotel.models.Customer;
import com.booking.hotel.models.Hotel;
import com.booking.hotel.enums.PaymentType;
import com.booking.hotel.enums.RoomType;

import java.time.LocalDate;

public interface HotelService {

    // Create booking
    Booking createBooking(Customer customer,
                          Hotel hotel,
                          RoomType roomType,
                          LocalDate checkIn,
                          LocalDate checkOut);

    // Confirm booking
    Booking confirmBooking(String bookingId);

    // Complete booking (checkout)
    Booking completeBooking(String bookingId);

    // Cancel booking
    Booking cancelBooking(String bookingId);

    // Process payment
    void processPayment(String bookingId,
                        PaymentType paymentType);
}