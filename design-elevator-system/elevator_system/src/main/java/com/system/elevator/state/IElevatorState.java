package com.system.elevator.state;

import com.system.elevator.model.Elevator;
import com.system.elevator.model.ElevatorRequest;
import com.system.elevator.model.Floor;

public interface IElevatorState {
    void step(Elevator elevator);
    void addRequest(Elevator elevator, ElevatorRequest request);
    void arrivedAtFloor(Elevator elevator);
}
