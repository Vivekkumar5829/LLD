package com.elevatorsystem;

import com.elevatorsystem.enums.Direction;
import com.elevatorsystem.enums.ElevatorState;

import java.util.List;

public class NearestElevatorStrategy implements SchedulingStrategy {
    @Override
    public Elevator selectElevator(List<Elevator> elevators, ElevatorRequest request) {
        Elevator best = null;
        int minDistance = Integer.MAX_VALUE;

        for (Elevator e : elevators) {
            if (e.getState() == ElevatorState.MAINTENANCE) continue;

            int distance = Math.abs(e.getCurrentFloor() - request.getFloor());

            // Prefer elevators already moving toward the request
            boolean movingTowards =
                    (e.getDirection() == Direction.UP && request.getFloor() >= e.getCurrentFloor()) ||
                            (e.getDirection() == Direction.DOWN && request.getFloor() <= e.getCurrentFloor()) ||
                            (e.getDirection() == Direction.IDLE);

            if (movingTowards && distance < minDistance) {
                minDistance = distance;
                best = e;
            }
        }
        return best;
    }
}
