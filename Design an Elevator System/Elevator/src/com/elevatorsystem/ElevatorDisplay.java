package com.elevatorsystem;

import com.elevatorsystem.enums.ElevatorState;

public class ElevatorDisplay implements ElevatorObserver{
    @Override
    public void onStateChange(Elevator elevator, ElevatorState newState) {
        System.out.println("Elevator " + elevator.getId() + "is now " + newState);
    }

    @Override
    public void onFloorChange(Elevator elevator, int newFloor) {
        System.out.println("Elevator " + elevator.getId() + " at floor " + newFloor);
    }
}
