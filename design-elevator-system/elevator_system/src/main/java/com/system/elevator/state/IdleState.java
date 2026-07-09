package com.system.elevator.state;

import com.system.elevator.model.Elevator;
import com.system.elevator.model.ElevatorRequest;

public class IdleState implements IElevatorState{
    @Override
    public void step(Elevator elevator) {
        System.out.println("Elevator " + elevator.getId() + " is IDLE");
    }

    @Override
    public void addRequest(Elevator elevator, ElevatorRequest request) {
        elevator.addRequest(request);
        System.out.println("Elevator " + elevator.getId() + " received request. Starting.");
    }

    @Override
    public void arrivedAtFloor(Elevator elevator) {
        throw new IllegalStateException(
                "Cannot arrive — elevator is IDLE.");
    }
}
