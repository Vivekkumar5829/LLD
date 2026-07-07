package com.car.booking.state;

import com.car.booking.model.Ride;

public class CompletedState implements IRideState {
    @Override
    public void acceptRide(Ride ride) {
        throw new IllegalStateException("Ride already completed.");
    }

    @Override
    public void startRide(Ride ride) {
        throw new IllegalStateException("Ride already completed.");
    }

    @Override
    public void endRide(Ride ride) {
        throw new IllegalStateException("Ride already completed.");
    }

    @Override
    public void cancelRide(Ride ride) {
        throw new IllegalStateException(
                "Cannot cancel a completed ride.");
    }
}
