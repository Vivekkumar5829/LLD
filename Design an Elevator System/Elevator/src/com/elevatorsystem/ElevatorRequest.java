package com.elevatorsystem;

import com.elevatorsystem.enums.Direction;
import com.elevatorsystem.enums.RequestType;

import java.util.Objects;

public class ElevatorRequest implements Comparable<ElevatorRequest>{

    private final int floor;
    private final Direction direction;
    private final RequestType type;
    private final long timestamp;

    public ElevatorRequest(int floor, Direction direction, RequestType type) {
        this.floor = floor;
        this.direction = direction;
        this.type = type;
        this.timestamp = System.currentTimeMillis();
    }

    public int getFloor() {
        return floor;
    }

    public Direction getDirection() {
        return direction;
    }

    public RequestType getType() {
        return type;
    }

    public long getTimestamp() {
        return timestamp;
    }

    @Override
    public int compareTo(ElevatorRequest other) {
        return Integer.compare(this.floor, other.floor);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ElevatorRequest)) return false;
        ElevatorRequest r = (ElevatorRequest) o;
        return this.floor == r.floor && this.type == r.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(floor, type);
    }
}
