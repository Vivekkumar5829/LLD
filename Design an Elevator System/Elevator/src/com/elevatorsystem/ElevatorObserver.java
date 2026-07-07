package com.elevatorsystem;

import com.elevatorsystem.enums.ElevatorState;

public interface ElevatorObserver {
    void onStateChange(Elevator elevator, ElevatorState newState);
    void onFloorChange(Elevator elevator, int newFloor);
}
