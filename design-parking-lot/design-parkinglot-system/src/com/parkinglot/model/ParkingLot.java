package com.parkinglot.model;

import com.parkinglot.enums.VehicleType;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {

    // Singleton
    private static volatile ParkingLot instance;

    private final String id;
    private final String name;
    private final List<ParkingFloor> floors;

    private ParkingLot(String id, String name) {
        this.id = id;
        this.name = name;
        this.floors = new ArrayList<>();
    }

    // Double checked locking
    public static ParkingLot getInstance(
            String id, String name) {
        if (instance == null) {
            synchronized (ParkingLot.class) {
                if (instance == null) {
                    instance = new ParkingLot(id, name);
                }
            }
        }
        return instance;
    }

    public void addFloor(ParkingFloor floor) {
        floors.add(floor);
    }

    // Core method — find available slot
    public ParkingSlot findAvailableSlot(
            VehicleType type) {
        for (ParkingFloor floor : floors) {
            ParkingSlot slot =
                    floor.findAvailableSlot(type);
            if (slot != null) return slot;
        }
        return null;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public List<ParkingFloor> getFloors() {
        return floors;
    }
}