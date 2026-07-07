package com.car.booking.state;

import com.car.booking.model.Ride;

public interface IRideState {
    void acceptRide(Ride ride);
    void startRide(Ride ride);
    void endRide(Ride ride);
    void cancelRide(Ride ride);
}
