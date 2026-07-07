package com.parkinglot.model;

import com.parkinglot.enums.VehicleType;

import java.util.ArrayList;
import java.util.List;

public class ParkingFloor {
    private final int floorNumber;
    private final List<ParkingSlot> slots;

    public ParkingFloor(int floorNumber) {
        this.floorNumber = floorNumber;
        this.slots = new ArrayList<>();
    }

    public void addSlot(ParkingSlot slot) {
        slots.add(slot);
    }

    public ParkingSlot findAvailableSlot(VehicleType type) {
        for (ParkingSlot slot : slots) {
            if(slot.isAvailable() && slot.getSlotType().name().equals(type.name())) {
                return slot;
            }
         }
        return null;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public List<ParkingSlot> getSlots() {
        return slots;
    }
}
