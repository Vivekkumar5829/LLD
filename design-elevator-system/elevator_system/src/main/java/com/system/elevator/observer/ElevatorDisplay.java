package com.system.elevator.observer;

import com.system.elevator.enums.ElevatorState;
import com.system.elevator.model.Elevator;

public class ElevatorDisplay
        implements ElevatorObserver {
    public void onStateChange(Elevator e,
                              ElevatorState s) {
        System.out.println("Elevator " +
                e.getId() + " → " + s);
    }

    @Override
    public void onFloorChange(Elevator e, int floor) {
        System.out.println("Elevator " +
                e.getId() + " at floor " + floor);
    }

}
