package com.system.elevator.strategy;

import com.system.elevator.model.Elevator;
import com.system.elevator.model.ElevatorRequest;

import java.util.List;

public interface SchedulingStrategy {
    Elevator selectElevator(
            List<Elevator> elevators,
            ElevatorRequest request);
}
