package com.booking.hotel.observer;

import com.booking.hotel.models.Booking;

public class CustomerNotification implements BookingObserver {

    @Override
    public void onBookingEvent(Booking booking, String event) {
        switch (event) {
            case "PENDING":
                System.out.println(
                        "[NOTIFICATION] Dear " +
                                booking.getCustomer().getName() +
                                ", your booking request received." +
                                " Awaiting confirmation.");
                break;

            case "CONFIRMED":
                System.out.println(
                        "[NOTIFICATION] Dear " +
                                booking.getCustomer().getName() +
                                ", booking CONFIRMED!" +
                                "\nHotel: " + booking.getHotel().getName() +
                                "\nRoom: " + booking.getRoom().getRoomNumber() +
                                "\nCheck-in: " + booking.getCheckIn() +
                                "\nCheck-out: " + booking.getCheckOut() +
                                "\nTotal: ₹" + booking.getTotalPrice());
                break;

            case "COMPLETED":
                System.out.println(
                        "[NOTIFICATION] Dear " +
                                booking.getCustomer().getName() +
                                ", your stay is complete." +
                                " Total paid: ₹" + booking.getTotalPrice() +
                                ". Visit again!");
                break;

            case "CANCELLED":
                System.out.println(
                        "[NOTIFICATION] Dear " +
                                booking.getCustomer().getName() +
                                ", your booking has been cancelled." +
                                " Booking ID: " + booking.getBookingId());
                break;

            default:
                System.out.println(
                        "[NOTIFICATION] Booking status update: "
                                + event);
        }
    }
}