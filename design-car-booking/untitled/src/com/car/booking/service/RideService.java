package com.car.booking.service;

import com.car.booking.model.Driver;
import com.car.booking.model.Location;
import com.car.booking.model.Ride;
import com.car.booking.model.Rider;
import com.car.booking.model.enums.PaymentType;
import com.car.booking.model.enums.VehicleType;

public interface RideService {

    Ride bookRide(Rider rider,
                  Location pickup,
                  Location drop,
                  VehicleType vehicleType);

    Ride acceptRide(String rideId, Driver driver);

    Ride startRide(String rideId);

    Ride endRide(String rideId);

    Ride cancelRide(String rideId);

    void makePayment(String rideId, PaymentType paymentType);
}