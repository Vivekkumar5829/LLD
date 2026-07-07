package com.elevatorsystem;

import com.elevatorsystem.enums.Direction;
import com.elevatorsystem.enums.RequestType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ElevatorController {
    private static volatile ElevatorController instance;
    private final List<Elevator> elevators;
    private SchedulingStrategy strategy;

    private ElevatorController() {
        this.elevators = new ArrayList<>();
        this.strategy = new NearestElevatorStrategy();
    }

    public static ElevatorController getInstance() {
        if (instance == null) {
            synchronized (ElevatorController.class) {
                if (instance == null) instance = new ElevatorController();
            }
        }
        return instance;
    }

    public void addElevator(Elevator elevator) {
        elevators.add(elevator);
    }

    public void setStrategy(SchedulingStrategy strategy) {
        this.strategy = strategy;
    }

    // EXTERNAL request — button pressed on a floor
    public synchronized void requestElevator(int floor, Direction direction) {
        ElevatorRequest request = new ElevatorRequest(floor, direction, RequestType.EXTERNAL);
        Elevator chosen = strategy.selectElevator(elevators, request);

        if (chosen == null) {
            System.out.println("No elevator available currently for floor " + floor);
            return;
        }
        chosen.addRequest(request);
    }

    // INTERNAL request — destination button pressed inside elevator
    public synchronized void requestFloor(int elevatorId, int destinationFloor) {
        Elevator elevator = getElevatorById(elevatorId);
        if (elevator == null) return;

        Direction dir = destinationFloor > elevator.getCurrentFloor() ? Direction.UP : Direction.DOWN;
        elevator.addRequest(new ElevatorRequest(destinationFloor, dir, RequestType.INTERNAL));
    }

    public void stepAll() {
        for (Elevator e : elevators) e.step();
    }

    private Elevator getElevatorById(int id) {
        for (Elevator e : elevators) {
            if (e.getId() == id) return e;
        }
        return null;
    }

    public List<Elevator> getElevators() {
        return Collections.unmodifiableList(elevators);
    }
}
