package com.car.booking.state;

import com.car.booking.model.Ride;
import com.car.booking.model.enums.RideStateEnum;

import java.time.LocalDateTime;

public class RequestedState implements IRideState{
    @Override
    public void acceptRide(Ride ride) {
        System.out.println("Driver accepted. En route");
        ride.setState(new DriverEnRouteState(), RideStateEnum.DRIVER_EN_ROUTE);
        ride.notifyObservers("ACCEPTED");
    }

    @Override
    public void startRide(Ride ride) {

        throw new IllegalStateException("Cannot start - driver hasn't arrived yet");
    }

    @Override
    public void endRide(Ride ride) {
        throw new IllegalStateException(
                "Cannot end — ride hasn't started.");
    }

    @Override
    public void cancelRide(Ride ride) {
        System.out.println("Cancelled at REQUESTED stage. No penalty.");
        ride.setEndTime(LocalDateTime.now());
        ride.setState(new CancelledState(),
                RideStateEnum.CANCELLED);
        ride.notifyObservers("CANCELLED");
    }
}
