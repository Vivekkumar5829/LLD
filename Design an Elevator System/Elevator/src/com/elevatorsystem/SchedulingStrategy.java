package com.elevatorsystem;

import java.util.List;

public interface SchedulingStrategy {
    Elevator selectElevator(List<Elevator> elevator, ElevatorRequest request);
}
