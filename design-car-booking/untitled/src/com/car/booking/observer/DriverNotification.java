package com.car.booking.observer;

import com.car.booking.model.Ride;

public class DriverNotification implements RideObserver {

    @Override
    public void onRideRequested(Ride ride) {
        System.out.println("[DRIVER NOTIFICATION] New ride request:");
        System.out.println("Pickup: " + ride.getPickupLocation());
        System.out.println("Drop: " + ride.getDropLocation());
        System.out.println("Rider: " + ride.getRider().getName());
    }

    @Override
    public void onRideAccepted(Ride ride) {
        System.out.println("[DRIVER NOTIFICATION] Ride accepted:");
        System.out.println("Driver assigned for rider: " + ride.getRider().getName());
    }

    @Override
    public void onRideStarted(Ride ride) {
        System.out.println("[DRIVER NOTIFICATION] Ride started:");
        System.out.println("Rider: " + ride.getRider().getName());
        System.out.println("From: " + ride.getPickupLocation() + " To: " + ride.getDropLocation());
    }

    @Override
    public void onRideCompleted(Ride ride) {
        System.out.println("[DRIVER NOTIFICATION] Ride completed:");
        System.out.println("Rider: " + ride.getRider().getName());
        System.out.println("Fare earned: " + ride.getFare());
    }

    @Override
    public void onRideCancelled(Ride ride) {
        System.out.println("[DRIVER NOTIFICATION] Ride cancelled:");
        System.out.println("Rider: " + ride.getRider().getName());
    }
}