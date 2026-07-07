package com.parkinglot.state;

import com.parkinglot.model.ParkingSlot;
import com.parkinglot.model.Vehicle;

public interface ISlotState {
    void occupy(ParkingSlot slot, Vehicle vehicle);
    void vacate(ParkingSlot slot);
}
