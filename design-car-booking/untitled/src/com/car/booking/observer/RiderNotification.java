package com.car.booking.observer;

import com.car.booking.model.Ride;

public class RiderNotification implements RideObserver {

    @Override
    public void onRideRequested(Ride ride) {
        System.out.println("[RIDER NOTIFICATION] Ride requested successfully");
        System.out.println("Searching driver for rider: " + ride.getRider().getName());
    }

    @Override
    public void onRideAccepted(Ride ride) {
        System.out.println("[RIDER NOTIFICATION] Driver assigned!");
        System.out.println("Driver: " + ride.getDriver().getName());
        System.out.println("Rating: " + ride.getDriver().getRating());
        System.out.println("Vehicle: " +
                ride.getDriver().getVehicle().getVehicleType() +
                " - " + ride.getDriver().getVehicle().getModel());
        System.out.println("Pickup: " + ride.getPickupLocation());
    }

    @Override
    public void onRideStarted(Ride ride) {
        System.out.println("[RIDER NOTIFICATION] Ride started");
        System.out.println("Trip started! Heading to: " + ride.getDropLocation());

    }

    @Override
    public void onRideCompleted(Ride ride) {
        System.out.println("[RIDER NOTIFICATION] Ride completed successfully");
        System.out.println("Drop location: " + ride.getDropLocation());
        System.out.println("Total fare: " + ride.getFare());
    }

    @Override
    public void onRideCancelled(Ride ride) {
        System.out.println("[RIDER NOTIFICATION] Ride cancelled");
        System.out.println("Reason: System / Driver / Rider cancellation");
    }
}