package com.system.elevator.strategy;

import com.system.elevator.enums.Direction;
import com.system.elevator.enums.ElevatorState;
import com.system.elevator.model.Elevator;
import com.system.elevator.model.ElevatorRequest;

import java.util.List;

public class ScanSchedulingStrategy
        implements SchedulingStrategy {

    @Override
    public Elevator selectElevator(
            List<Elevator> elevators,
            ElevatorRequest request) {
        Elevator best = null;
        int minDistance = Integer.MAX_VALUE;

        for (Elevator e : elevators) {
            // Skip maintenance elevators
            if (e.getState() ==
                    ElevatorState.MAINTENANCE)
                continue;

            int distance = Math.abs(
                    e.getCurrentFloor() -
                            request.getFloorNumber());

            // SCAN logic:
            // Prefer elevator already moving
            // in same direction AND
            // hasn't passed the floor yet
            boolean sameDirection =
                    (e.getDirection() == Direction.UP &&
                            request.getDirection() == Direction.UP &&
                            request.getFloorNumber() >=
                                    e.getCurrentFloor())
                            ||
                            (e.getDirection() == Direction.DOWN &&
                                    request.getDirection() == Direction.DOWN &&
                                    request.getFloorNumber() <=
                                            e.getCurrentFloor());

            boolean isIdle =
                    e.getDirection() == Direction.IDLE;

            if ((sameDirection || isIdle) &&
                    distance < minDistance) {
                minDistance = distance;
                best = e;
            }
        }

        // Fallback — any available elevator
        if (best == null) {
            for (Elevator e : elevators) {
                if (e.getState() !=
                        ElevatorState.MAINTENANCE) {
                    int distance = Math.abs(
                            e.getCurrentFloor() -
                                    request.getFloorNumber());
                    if (distance < minDistance) {
                        minDistance = distance;
                        best = e;
                    }
                }
            }
        }

        return best;
    }
}