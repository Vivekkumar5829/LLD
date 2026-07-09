package com.system.elevator.state;

import com.system.elevator.model.Elevator;
import com.system.elevator.model.ElevatorRequest;

public class MovingState implements IElevatorState {
    @Override
    public void step(Elevator elevator) {

        elevator.step();
        System.out.println("Elevator " +
                elevator.getId() + " moving. " +
                "Floor: " + elevator.getCurrentFloor());
    }

    @Override
    public void addRequest(Elevator elevator,
                           ElevatorRequest request) {

        elevator.addRequest(request);
        System.out.println("Request added " +
                "to moving elevator " +
                elevator.getId());
    }

    @Override
    public void arrivedAtFloor(Elevator elevator) {
        System.out.println("Elevator " +
                elevator.getId() +
                " arrived at floor " +
                elevator.getCurrentFloor());
    }
}
