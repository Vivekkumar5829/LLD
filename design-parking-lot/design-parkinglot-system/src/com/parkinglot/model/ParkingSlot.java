package com.parkinglot.model;

import com.parkinglot.enums.SlotType;
import com.parkinglot.observer.ISlotObserver;
import com.parkinglot.state.AvailableState;
import com.parkinglot.state.ISlotState;

import java.util.ArrayList;
import java.util.List;

public class ParkingSlot {
    private final int slotId;
    private final SlotType slotType;
    private volatile boolean isAvailable;
    private Vehicle currentVehicle;
    private ISlotState currentState;
    private List<ISlotObserver> observers;

    public ParkingSlot(int slotId, SlotType slotType) {
        this.slotId = slotId;
        this.slotType = slotType;
        this.isAvailable = true;
        this.currentState = new AvailableState();
        this.observers = new ArrayList<>();
    }

    // Delegates to State Pattern
    public synchronized void occupy(Vehicle vehicle) {
        currentState.occupy(this, vehicle);
        this.isAvailable = false;
    }

    public synchronized void vacate() {
        currentState.vacate(this);
        this.isAvailable = true;
    }

    // Called by State objects
    public void setState(ISlotState newState) {
        this.currentState = newState;
    }

    public void setCurrentVehicle(Vehicle vehicle) {
        this.currentVehicle = vehicle;
    }

    // Observer support
    public void addObserver(ISlotObserver observer) {
        observers.add(observer);
    }

    public void notifyObservers(String event) {
        for (ISlotObserver observer : observers) {
            if (event.equals("OCCUPIED"))
                observer.onSlotOccupied(this);
            else
                observer.onSlotVacated(this);
        }
    }

    public boolean isAvailable() { return isAvailable; }
    public SlotType getSlotType() { return slotType; }
    public int getSlotId() { return slotId; }
    public Vehicle getCurrentVehicle() {
        return currentVehicle;
    }
}