package com.booking.hotel.controller;

import com.booking.hotel.enums.PaymentType;
import com.booking.hotel.enums.RoomType;
import com.booking.hotel.models.Booking;
import com.booking.hotel.models.Customer;
import com.booking.hotel.models.Hotel;
import com.booking.hotel.service.HotelService;
import com.booking.hotel.service.HotelServiceImpl;
import com.booking.hotel.strategy.pricing.StandardPricingStrategy;

import java.time.LocalDate;

public class HotelController {

    // Singleton
    private static volatile HotelController instance;
    private final HotelService hotelService;

    private HotelController() {
        this.hotelService = new HotelServiceImpl(
                new StandardPricingStrategy()
        );
    }

    public static HotelController getInstance() {
        if (instance == null) {
            synchronized (HotelController.class) {
                if (instance == null) {
                    instance = new HotelController();
                }
            }
        }
        return instance;
    }

    public Booking createBooking(Customer customer,
                                 Hotel hotel,
                                 RoomType roomType,
                                 LocalDate checkIn,
                                 LocalDate checkOut) {
        System.out.println("\n--- CREATING BOOKING ---");
        return hotelService.createBooking(
                customer, hotel, roomType, checkIn, checkOut);
    }

    public Booking confirmBooking(String bookingId) {
        System.out.println("\n--- CONFIRMING BOOKING ---");
        return hotelService.confirmBooking(bookingId);
    }

    public Booking completeBooking(String bookingId) {
        System.out.println("\n--- COMPLETING BOOKING ---");
        return hotelService.completeBooking(bookingId);
    }

    public Booking cancelBooking(String bookingId) {
        System.out.println("\n--- CANCELLING BOOKING ---");
        return hotelService.cancelBooking(bookingId);
    }

    public void processPayment(String bookingId,
                               PaymentType paymentType) {
        System.out.println("\n--- PROCESSING PAYMENT ---");
        hotelService.processPayment(bookingId, paymentType);
    }
}