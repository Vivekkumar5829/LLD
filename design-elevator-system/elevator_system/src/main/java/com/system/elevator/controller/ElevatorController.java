package com.system.elevator.controller;

import com.system.elevator.enums.Direction;
import com.system.elevator.enums.RequestType;
import com.system.elevator.model.Elevator;
import com.system.elevator.model.ElevatorRequest;
import com.system.elevator.strategy.NearestElevatorStrategy;
import com.system.elevator.strategy.SchedulingStrategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ElevatorController {
    private static volatile ElevatorController
            instance;
    private final List<Elevator> elevators;
    private SchedulingStrategy strategy;

    private ElevatorController() {
        this.elevators = new ArrayList<>();
        this.strategy =
                new NearestElevatorStrategy();
    }

    public static ElevatorController getInstance() {
        if (instance == null) {
            synchronized (ElevatorController.class) {
                if (instance == null)
                    instance =
                            new ElevatorController();
            }
        }
        return instance;
    }

    public void addElevator(Elevator elevator) {
        elevators.add(elevator);
    }

    // EXTERNAL request
    public synchronized void requestElevator(
            int floor,
            Direction direction) {
        ElevatorRequest request =
                new ElevatorRequest(
                        floor, RequestType.EXTERNAL,
                        direction);
        Elevator chosen =
                strategy.selectElevator(
                        elevators, request);
        if (chosen == null) {
            System.out.println(
                    "No elevator available");
            return;
        }
        chosen.addRequest(request);
    }

    // INTERNAL request
    public synchronized void requestFloor(
            int elevatorId,
            int floor) {
        for (Elevator e : elevators) {
            if (e.getId() == elevatorId) {
                Direction dir =
                        floor > e.getCurrentFloor()
                                ? Direction.UP
                                : Direction.DOWN;
                e.addRequest(new ElevatorRequest(
                        floor, RequestType.INTERNAL,
                        dir));
                return;
            }
        }
    }

    public void stepAll() {
        for (Elevator e : elevators) e.step();
    }

    public List<Elevator> getElevators() {
        return Collections
                .unmodifiableList(elevators);
    }
}
