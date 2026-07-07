package com.car.booking.state;

import com.car.booking.model.Ride;

public class CancelledState implements IRideState {
    @Override
    public void acceptRide(Ride ride) {
        throw new IllegalStateException("Ride is cancelled.");
    }

    @Override
    public void startRide(Ride ride) {
        throw new IllegalStateException("Ride is cancelled.");
    }

    @Override
    public void endRide(Ride ride) {
        throw new IllegalStateException("Ride is cancelled.");
    }

    @Override
    public void cancelRide(Ride ride) {
        throw new IllegalStateException("Already cancelled.");
    }
}
