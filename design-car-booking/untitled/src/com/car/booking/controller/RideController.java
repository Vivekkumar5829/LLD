package com.car.booking.controller;

import com.car.booking.model.*;
import com.car.booking.model.enums.*;
import com.car.booking.service.RideService;
import com.car.booking.service.RideServiceImpl;
import com.car.booking.strategy.fare.PerKmFareStrategy;
import com.car.booking.strategy.matching.NearestDriverStrategy;

import java.util.List;

public class RideController {

    // Singleton — volatile for thread safety
    private static volatile RideController instance;

    // Delegate all logic to service
    private final RideService rideService;

    private RideController(List<Driver> drivers) {
        // RideController decides which strategies to use
        this.rideService = new RideServiceImpl(
                new NearestDriverStrategy(),
                new PerKmFareStrategy(),
                drivers
        );
    }

    // Double-checked locking — thread safe Singleton
    public static RideController getInstance(List<Driver> drivers) {
        if (instance == null) {
            synchronized (RideController.class) {
                if (instance == null) {
                    instance = new RideController(drivers);
                }
            }
        }
        return instance;
    }

    // BOOK — rider requests a ride
    public Ride bookRide(Rider rider,
                         Location pickup,
                         Location drop,
                         VehicleType vehicleType) {
        System.out.println("\n--- BOOKING RIDE ---");
        return rideService.bookRide(rider, pickup, drop, vehicleType);
    }

    // ACCEPT — driver accepts the ride
    public Ride acceptRide(String rideId, Driver driver) {
        System.out.println("\n--- ACCEPTING RIDE ---");
        return rideService.acceptRide(rideId, driver);
    }

    // START — driver arrives, trip begins
    public Ride startRide(String rideId) {
        System.out.println("\n--- STARTING RIDE ---");
        return rideService.startRide(rideId);
    }

    // END — trip completed
    public Ride endRide(String rideId) {
        System.out.println("\n--- ENDING RIDE ---");
        return rideService.endRide(rideId);
    }

    // CANCEL — rider or driver cancels
    public Ride cancelRide(String rideId) {
        System.out.println("\n--- CANCELLING RIDE ---");
        return rideService.cancelRide(rideId);
    }

    // PAYMENT — rider pays after ride
    public void makePayment(String rideId, PaymentType paymentType) {
        System.out.println("\n--- PROCESSING PAYMENT ---");
        rideService.makePayment(rideId, paymentType);
    }
}