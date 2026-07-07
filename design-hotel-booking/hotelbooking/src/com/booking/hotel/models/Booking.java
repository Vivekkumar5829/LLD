package com.booking.hotel.models;

import com.booking.hotel.enums.BookingState;
import com.booking.hotel.observer.BookingObserver;
import com.booking.hotel.state.IBookingState;
import com.booking.hotel.state.PendingState;
import com.booking.hotel.strategy.pricing.PricingStrategy;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Booking {
    private final String bookingId;
    private final Customer customer;
    private final Hotel hotel;
    private final Room room;
    private final LocalDate checkIn;
    private final LocalDate checkOut;
    private double totalPrice;
    private IBookingState currentState;
    private BookingState stateLabel;
    private final List<BookingObserver> observers;

    public Booking(Customer customer, Hotel hotel,
                   Room room, LocalDate checkIn,
                   LocalDate checkOut) {
        if (customer == null)
            throw new IllegalArgumentException(
                    "Customer cannot be null");
        if (room == null)
            throw new IllegalArgumentException(
                    "Room cannot be null");
        if (checkIn == null || checkOut == null)
            throw new IllegalArgumentException(
                    "Dates cannot be null");
        if (!checkOut.isAfter(checkIn))
            throw new IllegalArgumentException(
                    "Checkout must be after checkin");

        this.bookingId = UUID.randomUUID().toString();
        this.customer = customer;
        this.hotel = hotel;
        this.room = room;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.currentState = new PendingState(); // ← starts PENDING
        this.stateLabel = BookingState.PENDING;
        this.observers = new ArrayList<>();
    }

    // State delegation
    public void confirm()  { currentState.confirm(this);   }
    public void cancel()   { currentState.cancel(this);    }
    public void complete() { currentState.complete(this);  }

    // Called by State objects only — package private
    public void setState(IBookingState state, BookingState label) {
        this.currentState = state;
        this.stateLabel = label;
    }

    // Calculate price using strategy
    public void calculateTotalPrice(PricingStrategy strategy) {
        int nights = (int) ChronoUnit.DAYS.between(
                checkIn, checkOut);
        this.totalPrice = strategy.calculatePrice(room, nights);
    }

    // Observer support
    public void addObserver(BookingObserver observer) {
        observers.add(observer);
    }

    public void notifyObservers(String event) {
        for (BookingObserver observer : observers) {
            observer.onBookingEvent(this, event);
        }
    }

    // Getters
    public String getBookingId() { return bookingId; }
    public Customer getCustomer() { return customer; }
    public Hotel getHotel() { return hotel; }
    public Room getRoom() { return room; }
    public LocalDate getCheckIn() { return checkIn; }
    public LocalDate getCheckOut() { return checkOut; }
    public double getTotalPrice() { return totalPrice; }
    public BookingState getStateLabel() { return stateLabel; }

    @Override
    public String toString() {
        return "Booking{" +
                "id='" + bookingId + '\'' +
                ", customer=" + customer.getName() +
                ", room=" + room.getRoomNumber() +
                ", checkIn=" + checkIn +
                ", checkOut=" + checkOut +
                ", price=" + totalPrice +
                ", state=" + stateLabel +
                '}';
    }
}