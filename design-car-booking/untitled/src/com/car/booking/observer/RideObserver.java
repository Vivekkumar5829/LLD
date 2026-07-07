package com.car.booking.observer;

import com.car.booking.model.Ride;

public interface RideObserver {
    void onRideRequested(Ride ride);
    void onRideAccepted(Ride ride);
    void onRideStarted(Ride ride);
    void onRideCompleted(Ride ride);
    void onRideCancelled(Ride ride);
}
