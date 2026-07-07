package com.car.booking.service;

import com.car.booking.model.*;
import com.car.booking.model.enums.*;
import com.car.booking.observer.*;
import com.car.booking.strategy.fare.FareStrategy;
import com.car.booking.strategy.matching.DriverMatchingStrategy;
import com.car.booking.strategy.payment.*;

import java.util.*;

public class RideServiceImpl implements RideService {

    private final Map<String, Ride> rideStore;
    private final List<Driver> drivers;
    private final DriverMatchingStrategy matchingStrategy;
    private final FareStrategy fareStrategy;
    private final List<RideObserver> observers;

    public RideServiceImpl(DriverMatchingStrategy matchingStrategy,
                           FareStrategy fareStrategy,
                           List<Driver> drivers) {
        this.matchingStrategy = matchingStrategy;
        this.fareStrategy = fareStrategy;
        this.drivers = drivers;
        this.rideStore = new HashMap<>();
        this.observers = new ArrayList<>();
        // Register default observers
        this.observers.add(new DriverNotification());
        this.observers.add(new RiderNotification());
    }

    // BOOK RIDE
    @Override
    public Ride bookRide(Rider rider, Location pickup,
                         Location drop, VehicleType vehicleType) {
        // Find driver
        Driver driver = matchingStrategy.findDriver(
                drivers, pickup, vehicleType);
        if (driver == null) {
            throw new RuntimeException(
                    "No driver available for " + vehicleType);
        }

        // Create ride — starts in REQUESTED state
        Ride ride = new Ride(rider, driver, pickup, drop, fareStrategy);

        // Attach observers
        for (RideObserver observer : observers) {
            ride.addObserver(observer);
        }

        // Notify — rider requested a ride
        ride.notifyObservers("REQUESTED");

        // Mark driver busy
        driver.updateState(DriverState.ON_TRIP);

        // Store ride
        rideStore.put(ride.getRideId(), ride);

        return ride;
    }

    // ACCEPT RIDE
    @Override
    public Ride acceptRide(String rideId, Driver driver) {
        Ride ride = getRideOrThrow(rideId);
        ride.acceptRide(); // State Pattern → DRIVER_EN_ROUTE
        return ride;
    }

    // START RIDE
    @Override
    public Ride startRide(String rideId) {
        Ride ride = getRideOrThrow(rideId);
        ride.startRide(); // State Pattern → ONGOING + startTime
        return ride;
    }

    // END RIDE
    @Override
    public Ride endRide(String rideId) {
        Ride ride = getRideOrThrow(rideId);
        ride.endRide(); // State Pattern → COMPLETED + fare + endTime

        // Free driver
        ride.getDriver().updateState(DriverState.AVAILABLE);

        // Add to history
        ride.getDriver().addRideToHistory(rideId);
        ride.getRider().addRideToHistory(rideId);

        return ride;
    }

    // CANCEL RIDE
    @Override
    public Ride cancelRide(String rideId) {
        Ride ride = getRideOrThrow(rideId);
        ride.cancelRide(); // State Pattern handles validation

        // Free driver if assigned
        Driver driver = ride.getDriver();
        if (driver != null) {
            driver.updateState(DriverState.AVAILABLE);
        }

        rideStore.remove(rideId);
        return ride;
    }

    // PAYMENT
    @Override
    public void makePayment(String rideId, PaymentType paymentType) {
        Ride ride = getRideOrThrow(rideId);

        // Validate ride is completed
        if (ride.getStateLabel() != RideStateEnum.COMPLETED) {
            throw new IllegalStateException(
                    "Cannot pay for incomplete ride");
        }

        double fare = ride.getFare();

        // Use PaymentStrategy — not just println
        PaymentStrategy strategy = getPaymentStrategy(paymentType);
        strategy.processPayment(fare);
    }

    // ── Private Helpers ──────────────────────────────

    private Ride getRideOrThrow(String rideId) {
        Ride ride = rideStore.get(rideId);
        if (ride == null) {
            throw new RuntimeException("Ride not found: " + rideId);
        }
        return ride;
    }

    private PaymentStrategy getPaymentStrategy(PaymentType type) {
        switch (type) {
            case CASH: return new CashPayment();
            case CARD: return new CardPayment();
            case UPI:  return new UPIPayment();
            default:
                throw new IllegalArgumentException(
                        "Unknown payment type: " + type);
        }
    }
}