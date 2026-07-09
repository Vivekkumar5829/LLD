package com.system.elevator.strategy;

import com.system.elevator.enums.Direction;
import com.system.elevator.enums.ElevatorState;
import com.system.elevator.model.Elevator;
import com.system.elevator.model.ElevatorRequest;

import java.util.List;

public class NearestElevatorStrategy implements SchedulingStrategy{
    @Override
    public Elevator selectElevator(List<Elevator> elevators, ElevatorRequest request) {
        Elevator best = null;
        int minDistance = Integer.MAX_VALUE;

        for (Elevator e : elevators) {
            if (e.getState() ==
                    ElevatorState.MAINTENANCE)
                continue;

            int distance = Math.abs(
                    e.getCurrentFloor() -
                            request.getFloorNumber());

            boolean movingTowards =
                    (e.getDirection() == Direction.UP &&
                            request.getFloorNumber() >=
                                    e.getCurrentFloor()) ||
                            (e.getDirection() == Direction.DOWN &&
                                    request.getFloorNumber() <=
                                            e.getCurrentFloor()) ||
                            (e.getDirection() == Direction.IDLE);

            if (movingTowards &&
                    distance < minDistance) {
                minDistance = distance;
                best = e;
            }
        }
        return best;
    }
}
