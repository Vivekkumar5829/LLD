package com.parkinglot;

import com.parkinglot.enums.*;
import com.parkinglot.factory.VehicleFactory;
import com.parkinglot.model.*;
import com.parkinglot.observer.DisplayBoard;
import com.parkinglot.service.ParkingService;
import com.parkinglot.strategy.pricing.HourlyPricing;

public class Main {
    public static void main(String[] args) {

        // Setup parking lot
        ParkingLot lot = ParkingLot.getInstance(
                "LOT001", "Phoenix Mall Parking");

        // Setup display board
        DisplayBoard display = new DisplayBoard();

        // Setup floor with slots
        ParkingFloor floor1 = new ParkingFloor(1);

        ParkingSlot bikeSlot1 =
                new ParkingSlot(1, SlotType.BIKE);
        ParkingSlot bikeSlot2 =
                new ParkingSlot(2, SlotType.BIKE);
        ParkingSlot carSlot1 =
                new ParkingSlot(3, SlotType.CAR);
        ParkingSlot carSlot2 =
                new ParkingSlot(4, SlotType.CAR);
        ParkingSlot truckSlot =
                new ParkingSlot(5, SlotType.TRUCK);

        // Attach observer to each slot
        bikeSlot1.addObserver(display);
        bikeSlot2.addObserver(display);
        carSlot1.addObserver(display);
        carSlot2.addObserver(display);
        truckSlot.addObserver(display);

        floor1.addSlot(bikeSlot1);
        floor1.addSlot(bikeSlot2);
        floor1.addSlot(carSlot1);
        floor1.addSlot(carSlot2);
        floor1.addSlot(truckSlot);
        lot.addFloor(floor1);

        // Setup service
        ParkingService service = new ParkingService(
                lot, new HourlyPricing());

        // Create vehicles
        Vehicle bike = VehicleFactory.createVehicle(
                "MH12AB1234", VehicleType.BIKE);
        Vehicle car = VehicleFactory.createVehicle(
                "MH14XY9999", VehicleType.CAR);
        Vehicle truck = VehicleFactory.createVehicle(
                "MH16ZZ0001", VehicleType.TRUCK);

        // ── Happy Path ──────────────────────────

        System.out.println("=== CHECK IN ===");
        ParkingTicket bikeTicket = service.checkIn(bike);
        ParkingTicket carTicket  = service.checkIn(car);
        ParkingTicket truckTicket = service.checkIn(truck);

        System.out.println("\n=== CHECK OUT ===");
        service.checkOut(
                bikeTicket.getTicketId(), PaymentType.UPI);
        service.checkOut(
                carTicket.getTicketId(), PaymentType.CARD);
        service.checkOut(
                truckTicket.getTicketId(), PaymentType.CASH);

        // ── Edge Case: No slot available ────────

        System.out.println("\n=== NO SLOT TEST ===");
        Vehicle car2 = VehicleFactory.createVehicle(
                "MH18AA5555", VehicleType.CAR);
        Vehicle car3 = VehicleFactory.createVehicle(
                "MH18BB6666", VehicleType.CAR);

        service.checkIn(car2); // fills slot 3
        service.checkIn(car3); // fills slot 4

        try {
            Vehicle car4 = VehicleFactory.createVehicle(
                    "MH18CC7777", VehicleType.CAR);
            service.checkIn(car4); // no slots left
        } catch (RuntimeException e) {
            System.out.println(
                    "Expected: " + e.getMessage());
        }

        // ── Edge Case: Illegal State ─────────────

        System.out.println("\n=== ILLEGAL STATE TEST ===");
        try {
            // Try to vacate already available slot
            bikeSlot2.vacate();
        } catch (IllegalStateException e) {
            System.out.println(
                    "Expected: " + e.getMessage());
        }
    }
}