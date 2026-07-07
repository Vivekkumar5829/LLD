package com.car.booking;

import com.car.booking.controller.RideController;
import com.car.booking.factory.VehicleFactory;
import com.car.booking.model.*;
import com.car.booking.model.enums.*;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        // Setup vehicles
        Vehicle sedan1 = VehicleFactory.createVehicle(
                "MH12AB1234", VehicleType.SEDAN, "Swift");
        Vehicle bike1 = VehicleFactory.createVehicle(
                "MH14XY9999", VehicleType.BIKE, "Pulsar");

        // Setup drivers
        Driver driver1 = new Driver(
                "Ramesh", "9876543210",
                sedan1, new Location(18.5, 73.8));
        Driver driver2 = new Driver(
                "Suresh", "9123456789",
                bike1, new Location(18.6, 73.9));

        List<Driver> drivers = Arrays.asList(driver1, driver2);

        // Setup rider
        Rider rider = new Rider(
                "Vivek", "9999999999",
                new Location(18.52, 73.85));

        // Locations
        Location pickup = new Location(18.52, 73.85);
        Location drop   = new Location(18.62, 73.95);

        // Get controller — Singleton
        RideController controller = RideController.getInstance(drivers);

        // ── Happy Path ──────────────────────────────

        // 1. Book
        Ride ride = controller.bookRide(
                rider, pickup, drop, VehicleType.SEDAN);
        System.out.println("Ride ID: " + ride.getRideId());

        // 2. Accept
        controller.acceptRide(ride.getRideId(), driver1);

        // 3. Start
        controller.startRide(ride.getRideId());

        // 4. End
        controller.endRide(ride.getRideId());

        // 5. Pay
        controller.makePayment(ride.getRideId(), PaymentType.UPI);

        // ── Edge Case: Cancel ────────────────────────

        System.out.println("\n\n=== CANCEL TEST ===");
        Ride ride2 = controller.bookRide(
                rider, pickup, drop, VehicleType.SEDAN);
        controller.cancelRide(ride2.getRideId());

        // ── Edge Case: Illegal State ─────────────────

        System.out.println("\n\n=== ILLEGAL STATE TEST ===");
        Ride ride3 = controller.bookRide(
                rider, pickup, drop, VehicleType.SEDAN);
        try {
            // Try to end ride before starting — should throw
            controller.endRide(ride3.getRideId());
        } catch (IllegalStateException e) {
            System.out.println("Caught expected error: " + e.getMessage());
        }
    }
}