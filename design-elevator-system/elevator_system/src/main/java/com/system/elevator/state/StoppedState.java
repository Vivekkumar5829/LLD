package com.system.elevator.state;

import com.system.elevator.model.Elevator;
import com.system.elevator.model.ElevatorRequest;

public class StoppedState implements IElevatorState {
    @Override
    public void step(Elevator elevator) {
        // Check if more requests exist
        System.out.println("Elevator " +
                elevator.getId() +
                " stopped at floor " +
                elevator.getCurrentFloor() +
                ". Doors open.");
    }

    @Override
    public void addRequest(Elevator elevator,
                           ElevatorRequest request) {
        elevator.addRequest(request);
    }

    @Override
    public void arrivedAtFloor(Elevator elevator) {
        throw new IllegalStateException(
                "Already stopped.");
    }
}
