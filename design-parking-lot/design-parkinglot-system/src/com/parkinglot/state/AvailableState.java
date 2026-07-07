package com.parkinglot.state;

import com.parkinglot.model.ParkingSlot;
import com.parkinglot.model.Vehicle;

public class AvailableState implements ISlotState{
    @Override
    public void occupy(ParkingSlot slot, Vehicle vehicle) {
        System.out.println("Slot " + slot.getSlotId() + " occupied.");
        slot.setCurrentVehicle(vehicle);
        slot.setState(new OccupiedState());
        slot.notifyObservers("Occupied");
    }

    @Override
    public void vacate(ParkingSlot slot) {
        throw new IllegalStateException("Cannot vacate unoccupied space.");
    }
}
