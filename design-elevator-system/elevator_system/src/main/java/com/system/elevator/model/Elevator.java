package com.system.elevator.model;

import com.system.elevator.enums.Direction;
import com.system.elevator.enums.ElevatorState;
import com.system.elevator.observer.ElevatorObserver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

public class Elevator {
    private final int id;
    private volatile int currentFloor;
    private volatile Direction direction;
    private volatile ElevatorState state;
    private final TreeSet<ElevatorRequest> upRequests;
    private final TreeSet<ElevatorRequest> downRequests;
    private final List<ElevatorObserver> observers;
    private final int maxCapacity;
    private int currentLoad;

    public Elevator(int id, int maxCapacity) {
        this.id = id;
        this.currentFloor = 0;
        this.direction = Direction.IDLE;
        this.state = ElevatorState.IDLE;
        this.upRequests = new TreeSet<>();
        this.downRequests = new TreeSet<>(
                Collections.reverseOrder());
        this.observers = new ArrayList<>();
        this.maxCapacity = maxCapacity;
        this.currentLoad = 0;
    }

    public synchronized void addRequest(
            ElevatorRequest request) {
        if (request.getFloorNumber() >= currentFloor) {
            upRequests.add(request);
        } else {
            downRequests.add(request);
        }

        if (state == ElevatorState.IDLE) {
            direction = request.getFloorNumber() > currentFloor
                    ? Direction.UP
                    : request.getFloorNumber() < currentFloor
                    ? Direction.DOWN
                    : Direction.IDLE;
            setState(ElevatorState.MOVING);
        }
    }

    public synchronized void step() {
        if (state != ElevatorState.MOVING) return;

        int nextStop = getNextStop();
        if (nextStop == currentFloor) {
            arriveAtFloor();
            return;
        }

        currentFloor += (nextStop > currentFloor) ? 1 : -1;
        notifyFloorChange();

        if (currentFloor == nextStop) {
            arriveAtFloor();
        }
    }

    private int getNextStop() {
        if (direction == Direction.UP) {
            if (!upRequests.isEmpty())
                return upRequests.first().getFloorNumber();
            if (!downRequests.isEmpty()) {
                direction = Direction.DOWN;
                return downRequests.first().getFloorNumber();
            }
        } else if (direction == Direction.DOWN) {
            if (!downRequests.isEmpty())
                return downRequests.first().getFloorNumber();
            if (!upRequests.isEmpty()) {
                direction = Direction.UP;
                return upRequests.first().getFloorNumber();
            }
        }
        return currentFloor;
    }

    private void arriveAtFloor() {
        setState(ElevatorState.STOPPED);
        upRequests.removeIf(
                r -> r.getFloorNumber() == currentFloor);
        downRequests.removeIf(
                r -> r.getFloorNumber() == currentFloor);

        if (upRequests.isEmpty() &&
                downRequests.isEmpty()) {
            direction = Direction.IDLE;
            setState(ElevatorState.IDLE);
        } else {
            setState(ElevatorState.MOVING);
        }
    }

    public synchronized boolean canAccommodate(
            int additionalLoad) {
        return currentLoad + additionalLoad <= maxCapacity;
    }

    public void addObserver(ElevatorObserver o) {
        observers.add(o);
    }

    private void setState(ElevatorState newState) {
        this.state = newState;
        for (ElevatorObserver o : observers)
            o.onStateChange(this, newState);
    }

    private void notifyFloorChange() {
        for (ElevatorObserver o : observers)
            o.onFloorChange(this, currentFloor);
    }

    public int getId() { return id; }
    public int getCurrentFloor() { return currentFloor; }
    public Direction getDirection() { return direction; }
    public ElevatorState getState() { return state; }
}