package com.parkinglot.model;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

public class ParkingTicket {
    private final String ticketId;
    private final Vehicle vehicle;
    private final ParkingSlot slot;
    private final LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private boolean isPaid;
    private double fare;

    public ParkingTicket(Vehicle vehicle,
                         ParkingSlot slot) {
        this.ticketId = UUID.randomUUID().toString();
        this.vehicle = vehicle;
        this.slot = slot;
        this.entryTime = LocalDateTime.now();
        this.isPaid = false;
    }

    public long getParkingDurationInHours() {
        LocalDateTime end = exitTime != null ?
                exitTime : LocalDateTime.now();
        long hours = ChronoUnit.HOURS.between(
                entryTime, end);
        return hours == 0 ? 1 : hours;
    }

    public void markExit() {
        this.exitTime = LocalDateTime.now();
    }

    public void markPaid(double fare) {
        this.fare = fare;
        this.isPaid = true;
    }

    public String getTicketId() { return ticketId; }
    public Vehicle getVehicle() { return vehicle; }
    public ParkingSlot getSlot() { return slot; }
    public LocalDateTime getEntryTime() { return entryTime; }
    public LocalDateTime getExitTime() { return exitTime; }
    public boolean isPaid() { return isPaid; }
    public double getFare() { return fare; }
}