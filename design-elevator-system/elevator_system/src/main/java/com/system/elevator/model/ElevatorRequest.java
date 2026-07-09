package com.system.elevator.model;

import com.system.elevator.enums.Direction;
import com.system.elevator.enums.RequestType;
import java.util.Objects;

public class ElevatorRequest
        implements Comparable<ElevatorRequest> {

    private final int floorNumber;
    private final RequestType requestType;
    private final Direction direction;
    private final long timestamp;

    public ElevatorRequest(int floorNumber,
                           RequestType requestType,
                           Direction direction) {
        this.floorNumber = floorNumber;
        this.requestType = requestType;
        this.direction = direction;
        this.timestamp = System.currentTimeMillis();
    }

    // TreeSet uses this to sort
    @Override
    public int compareTo(ElevatorRequest other) {
        if (this.floorNumber != other.floorNumber)
            return Integer.compare(
                    this.floorNumber,
                    other.floorNumber);
        // Same floor → sort by timestamp
        return Long.compare(
                this.timestamp, other.timestamp);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ElevatorRequest))
            return false;
        ElevatorRequest r = (ElevatorRequest) o;
        return floorNumber == r.floorNumber &&
                requestType == r.requestType &&
                direction == r.direction;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                floorNumber, requestType, direction);
    }

    public int getFloorNumber() {
        return floorNumber;
    }
    public RequestType getRequestType() {
        return requestType;
    }
    public Direction getDirection() {
        return direction;
    }
    public long getTimestamp() {
        return timestamp;
    }
}