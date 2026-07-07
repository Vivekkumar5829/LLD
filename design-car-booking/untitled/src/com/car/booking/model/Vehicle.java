package com.car.booking.model;

import com.car.booking.model.enums.VehicleType;

public class Vehicle {
    private VehicleType vehicleType;
    private String licensePlate;
    private  int capacity;
    private String model;


    public Vehicle(VehicleType vehicleType, String licensePlate, int capacity, String model) {
        if(capacity <= 0) {
            throw  new IllegalArgumentException("Capacity must be greater than 0");
        }

        if (licensePlate == null || licensePlate.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "License plate cannot be null or empty");
        }

        this.vehicleType = vehicleType;
        this.licensePlate = licensePlate;
        this.capacity = capacity;
        this.model = model;
    }
    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getModel() {
        return model;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "type=" + vehicleType +
                ", licensePlate='" + licensePlate + '\'' +
                ", capacity=" + capacity +
                ", model='" + model + '\'' +
                '}';
    }

}
