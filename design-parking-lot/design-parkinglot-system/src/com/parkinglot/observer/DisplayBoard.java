package com.parkinglot.observer;

import com.parkinglot.model.ParkingSlot;

public class DisplayBoard implements ISlotObserver {

    @Override
    public void onSlotOccupied(ParkingSlot slot) {
        System.out.println("[DISPLAY] Slot " +
                slot.getSlotId() + " [" +
                slot.getSlotType() + "] → OCCUPIED by " +
                slot.getCurrentVehicle().getLicensePlate());
    }

    @Override
    public void onSlotVacated(ParkingSlot slot) {
        System.out.println("[DISPLAY] Slot " +
                slot.getSlotId() + " [" +
                slot.getSlotType() + "] → AVAILABLE");
    }
}