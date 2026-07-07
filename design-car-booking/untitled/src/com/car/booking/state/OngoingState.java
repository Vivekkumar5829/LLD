package com.car.booking.state;

import com.car.booking.model.Ride;
import com.car.booking.model.enums.RideStateEnum;

import java.time.LocalDateTime;

// ONGOING — only end allowed
public class OngoingState implements IRideState {
    @Override
    public void acceptRide(Ride ride) {
        throw new IllegalStateException("Already ongoing.");
    }

    @Override
    public void startRide(Ride ride) {
        throw new IllegalStateException("Already started.");
    }

    @Override
    public void endRide(Ride ride) {
        System.out.println("Ride ended. Calculating fare.");
        ride.setEndTime(LocalDateTime.now());
        ride.calculateFare();
        ride.setState(new CompletedState(),
                RideStateEnum.COMPLETED);
        ride.notifyObservers("COMPLETED");
    }

    @Override
    public void cancelRide(Ride ride) {
        throw new IllegalStateException(
                "Cannot cancel an ongoing ride.");
    }
}