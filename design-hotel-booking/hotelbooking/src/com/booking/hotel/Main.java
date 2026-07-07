package com.booking.hotel;

import com.booking.hotel.controller.HotelController;
import com.booking.hotel.enums.AmenityType;
import com.booking.hotel.enums.PaymentType;
import com.booking.hotel.enums.RoomType;
import com.booking.hotel.factory.RoomFactory;
import com.booking.hotel.models.*;

import java.time.LocalDate;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        // Setup Hotel
        Address address = new Address(
                "MG Road", "Pune",
                "Maharashtra", "India", "411001");

        Hotel hotel = new Hotel(
                "Taj Pune", address,
                Arrays.asList(AmenityType.POOL,
                        AmenityType.GYM), 5);

        // Add rooms
        hotel.addRoom(RoomFactory.createRoom(
                RoomType.STANDARD, "101"));
        hotel.addRoom(RoomFactory.createRoom(
                RoomType.AC, "201"));
        hotel.addRoom(RoomFactory.createRoom(
                RoomType.SUITE, "301"));

        // Setup Customer
        Address customerAddress = new Address(
                "FC Road", "Pune",
                "Maharashtra", "India", "411004");

        Customer customer = new Customer(
                "Vivek", "vivek@gmail.com",
                "9999999999", customerAddress);

        // Get Controller
        HotelController controller =
                HotelController.getInstance();

        // ── Happy Path ──────────────────────────

        // 1. Create Booking
        Booking booking = controller.createBooking(
                customer, hotel, RoomType.AC,
                LocalDate.of(2026, 8, 1),
                LocalDate.of(2026, 8, 5));

        System.out.println("Booking ID: " +
                booking.getBookingId());

        // 2. Confirm
        controller.confirmBooking(booking.getBookingId());

        // 3. Pay
        controller.processPayment(
                booking.getBookingId(), PaymentType.UPI);

        // 4. Complete (checkout)
        controller.completeBooking(booking.getBookingId());

        // ── Cancel Test ──────────────────────────

        System.out.println("\n\n=== CANCEL TEST ===");
        Booking booking2 = controller.createBooking(
                customer, hotel, RoomType.STANDARD,
                LocalDate.of(2026, 9, 1),
                LocalDate.of(2026, 9, 3));

        controller.cancelBooking(booking2.getBookingId());

        // ── Illegal State Test ───────────────────

        System.out.println("\n\n=== ILLEGAL STATE TEST ===");
        Booking booking3 = controller.createBooking(
                customer, hotel, RoomType.SUITE,
                LocalDate.of(2026, 10, 1),
                LocalDate.of(2026, 10, 4));

        try {
            // Complete without confirming — should throw
            controller.completeBooking(
                    booking3.getBookingId());
        } catch (IllegalStateException e) {
            System.out.println(
                    "Caught expected error: " + e.getMessage());
        }
    }
}