package com.system.elevator.observer;

import com.system.elevator.enums.ElevatorState;
import com.system.elevator.model.Elevator;

public interface ElevatorObserver {
    void onStateChange(Elevator e, ElevatorState state);
    void onFloorChange(Elevator e, int floor);
}
