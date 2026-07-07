package com.car.booking.state;

import com.car.booking.model.Ride;
import com.car.booking.model.enums.RideStateEnum;

import java.time.LocalDateTime;

public class DriverEnRouteState implements IRideState {
    @Override
    public void acceptRide(Ride ride) {
        throw new IllegalStateException(
                "Already accepted.");
    }

    @Override
    public void startRide(Ride ride) {
        System.out.println("Ride started. Trip ongoing");
        ride.setStartTime(LocalDateTime.now());
        ride.setState(new OngoingState(), RideStateEnum.ONGOING);
        ride.notifyObservers("STARTED");
    }

    @Override
    public void endRide(Ride ride) {
        throw new IllegalStateException(
                "Cannot end — trip hasn't started.");
    }

    @Override
    public void cancelRide(Ride ride) {
        System.out.println("Cancelled after driver arrived. Penalty applies.");
        ride.setEndTime(LocalDateTime.now());
        ride.setState(new CancelledState(),
                RideStateEnum.CANCELLED);
        ride.notifyObservers("CANCELLED");
    }
}
