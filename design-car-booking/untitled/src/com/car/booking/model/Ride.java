package com.car.booking.model;

import com.car.booking.model.enums.DriverState;
import com.car.booking.model.enums.RideStateEnum;
import com.car.booking.observer.RideObserver;
import com.car.booking.state.IRideState;
import com.car.booking.state.RequestedState;

import com.car.booking.model.Driver;
import com.car.booking.strategy.fare.FareStrategy;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class Ride {
    private String rideId;
    private Rider rider;
    private Driver driver;
    private Location pickupLocation;
    private Location dropLocation;
    private IRideState currentState;
    private RideStateEnum stateLabel;
    private FareStrategy fareStrategy;
    private double fare;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private List<RideObserver> observers = new ArrayList<>();

    public Ride(Rider rider, Driver driver,
                Location pickupLocation, Location dropLocation,
                FareStrategy fareStrategy) {
        this.rideId = UUID.randomUUID().toString();
        this.rider = rider;
        this.driver = driver;
        this.pickupLocation = pickupLocation;
        this.dropLocation = dropLocation;
        this.fareStrategy = fareStrategy;
        this.currentState = new RequestedState(); // State Pattern
        this.stateLabel = RideStateEnum.REQUESTED;
    }


    public void acceptRide() {
        currentState.acceptRide(this);
    }

    public void startRide() {
        currentState.startRide(this);
    }

    public void endRide() {
        currentState.endRide(this);
    }

    public void cancelRide() {
        currentState.cancelRide(this);
    }


    public void setState(IRideState newState, RideStateEnum label) {
        this.currentState = newState;
        this.stateLabel = label;
    }

    public void setStartTime(LocalDateTime time) {
        this.startTime = time;
    }

    public void setEndTime(LocalDateTime time) {
        this.endTime = time;
    }

    public void calculateFare() {
        this.fare = fareStrategy.calculateFare(
                pickupLocation, dropLocation,driver.getVehicle().getVehicleType() );
    }

    public void addObserver(RideObserver observer) {
        observers.add(observer);
    }

    public void notifyObservers(String event) {
        for (RideObserver observer : observers) {
            switch (event) {
                case "REQUESTED":
                    observer.onRideRequested(this);
                    break;
                case "ACCEPTED":
                    observer.onRideAccepted(this);
                    break;
                case "STARTED":
                    observer.onRideStarted(this);
                    break;
                case "COMPLETED":
                    observer.onRideCompleted(this);
                    break;
                case "CANCELLED":
                    observer.onRideCancelled(this);
                    break;
            }
        }
    }

    public String getRideId() { return rideId; }
    public Rider getRider() { return rider; }
    public Driver getDriver() { return driver; }
    public Location getPickupLocation() { return pickupLocation; }
    public Location getDropLocation() { return dropLocation; }
    public RideStateEnum getStateLabel() { return stateLabel; }
    public double getFare() { return fare; }
    public LocalDateTime getStartTime() { return startTime; }
    public LocalDateTime getEndTime() { return endTime; }
}