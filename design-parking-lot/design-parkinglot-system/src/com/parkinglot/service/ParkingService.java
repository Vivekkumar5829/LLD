package com.parkinglot.service;

import com.parkinglot.enums.PaymentType;
import com.parkinglot.enums.VehicleType;
import com.parkinglot.model.*;
import com.parkinglot.strategy.payment.*;
import com.parkinglot.strategy.pricing.*;

import java.util.HashMap;
import java.util.Map;

public class ParkingService {

    private final ParkingLot parkingLot;
    private final PricingStrategy pricingStrategy;
    private final Map<String, ParkingTicket> activeTickets;

    public ParkingService(ParkingLot parkingLot,
                          PricingStrategy pricingStrategy) {
        this.parkingLot = parkingLot;
        this.pricingStrategy = pricingStrategy;
        this.activeTickets = new HashMap<>();
    }

    // ENTRY
    public ParkingTicket checkIn(Vehicle vehicle) {
        ParkingSlot slot = parkingLot
                .findAvailableSlot(
                        vehicle.getVehicleType());
        if (slot == null)
            throw new RuntimeException(
                    "No slot available for " +
                            vehicle.getVehicleType());

        slot.occupy(vehicle);
        ParkingTicket ticket = new ParkingTicket(
                vehicle, slot);
        activeTickets.put(ticket.getTicketId(), ticket);

        System.out.println("Checked in: " +
                vehicle.getLicensePlate() +
                " → Slot " +
                slot.getSlotId());
        return ticket;
    }

    // EXIT
    public double checkOut(String ticketId,
                           PaymentType paymentType) {
        ParkingTicket ticket = activeTickets.get(ticketId);
        if (ticket == null)
            throw new RuntimeException(
                    "Ticket not found: " + ticketId);

        // Mark exit time
        ticket.markExit();

        // Calculate fare
        double fare = pricingStrategy.calculateFare(
                ticket.getVehicle().getVehicleType(),
                ticket.getParkingDurationInHours());

        // Process payment
        PaymentStrategy payment =
                getPaymentStrategy(paymentType);
        payment.processPayment(fare);

        // Mark paid + free slot
        ticket.markPaid(fare);
        ticket.getSlot().vacate();

        // Remove from active
        activeTickets.remove(ticketId);

        System.out.println("Checked out: " +
                ticket.getVehicle()
                        .getLicensePlate() +
                " → Fare: ₹" + fare);
        return fare;
    }

    private PaymentStrategy getPaymentStrategy(
            PaymentType type) {
        switch (type) {
            case CASH: return new CashPayment();
            case CARD: return new CardPayment();
            case UPI:  return new UPIPayment();
            default:
                throw new IllegalArgumentException(
                        "Unknown payment type");
        }
    }
}