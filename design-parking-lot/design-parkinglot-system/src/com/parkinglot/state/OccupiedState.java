package com.parkinglot.state;

import com.parkinglot.model.ParkingSlot;
import com.parkinglot.model.Vehicle;

public class OccupiedState implements ISlotState{
    @Override
    public void occupy(ParkingSlot slot, Vehicle vehicle) {
        throw new IllegalArgumentException("Cannot occupy already occupied state");
    }

    @Override
    public void vacate(ParkingSlot slot) {
        System.out.println("Slot " + slot.getSlotId() + " Occupied.");
        slot.setCurrentVehicle(null);
        slot.setState(new AvailableState());
        slot.notifyObservers("AVAILABLE");
    }
}
