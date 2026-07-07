package com.booking.hotel.service;

import com.booking.hotel.enums.PaymentType;
import com.booking.hotel.enums.RoomType;
import com.booking.hotel.models.*;
import com.booking.hotel.observer.CustomerNotification;
import com.booking.hotel.strategy.payment.*;
import com.booking.hotel.strategy.pricing.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HotelServiceImpl implements HotelService {

    private final Map<String, Booking> bookingStore;
    private final PricingStrategy pricingStrategy;

    public HotelServiceImpl(PricingStrategy pricingStrategy) {
        this.bookingStore = new HashMap<>();
        this.pricingStrategy = pricingStrategy;
    }

    @Override
    public Booking createBooking(Customer customer,
                                 Hotel hotel,
                                 RoomType roomType,
                                 LocalDate checkIn,
                                 LocalDate checkOut) {
        // Find available room
        List<Room> available = hotel.getAvailableRooms(roomType);
        if (available.isEmpty()) {
            throw new RuntimeException(
                    "No available rooms of type: " + roomType);
        }

        // Pick first available room
        Room room = available.get(0);

        // Create booking
        Booking booking = new Booking(
                customer, hotel, room, checkIn, checkOut);

        // Calculate price
        booking.calculateTotalPrice(pricingStrategy);

        // Mark room unavailable
        room.markUnavailable();

        // Attach observer
        booking.addObserver(new CustomerNotification());

        // Notify
        booking.notifyObservers("PENDING");

        // Store
        bookingStore.put(booking.getBookingId(), booking);

        // Add to customer history
        customer.addBookingToHistory(booking.getBookingId());

        return booking;
    }

    @Override
    public Booking confirmBooking(String bookingId) {
        Booking booking = getBookingOrThrow(bookingId);
        booking.confirm();
        return booking;
    }

    @Override
    public Booking completeBooking(String bookingId) {
        Booking booking = getBookingOrThrow(bookingId);
        booking.complete();
        // Free room
        booking.getRoom().markAvailable();
        return booking;
    }

    @Override
    public Booking cancelBooking(String bookingId) {
        Booking booking = getBookingOrThrow(bookingId);
        booking.cancel();
        // Free room
        booking.getRoom().markAvailable();
        bookingStore.remove(bookingId);
        return booking;
    }

    @Override
    public void processPayment(String bookingId,
                               PaymentType paymentType) {
        Booking booking = getBookingOrThrow(bookingId);

        if (booking.getStateLabel() !=
                com.booking.hotel.enums.BookingState.CONFIRMED) {
            throw new IllegalStateException(
                    "Can only pay for confirmed bookings");
        }

        double amount = booking.getTotalPrice();
        PaymentStrategy strategy =
                getPaymentStrategy(paymentType);
        strategy.processPayment(amount);
    }

    // ── Private Helpers ──────────────────────────

    private Booking getBookingOrThrow(String bookingId) {
        Booking booking = bookingStore.get(bookingId);
        if (booking == null)
            throw new RuntimeException(
                    "Booking not found: " + bookingId);
        return booking;
    }

    private PaymentStrategy getPaymentStrategy(
            PaymentType type) {
        switch (type) {
            case CASH: return new CashPayment();
            case CARD: return new CardPayment();
            case UPI:  return new UPIPayment();
            default:
                throw new IllegalArgumentException(
                        "Unknown payment type: " + type);
        }
    }
}