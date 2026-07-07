package com.parkinglot.observer;

import com.parkinglot.model.ParkingSlot;

public interface ISlotObserver {
    void onSlotOccupied(ParkingSlot slot);
    void onSlotVacated(ParkingSlot slot);
}
