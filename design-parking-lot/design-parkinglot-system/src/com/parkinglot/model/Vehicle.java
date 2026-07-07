package com.parkinglot.model;

import com.parkinglot.enums.VehicleType;

public abstract class Vehicle {
    private String licensePlate;
    private VehicleType vehicleType;

    public Vehicle(String licensePlate, VehicleType vehicleType) {

        if(licensePlate == null || licensePlate.trim().isEmpty()) {
            throw new IllegalArgumentException("Enter a valid license plate number");
        }

        if(vehicleType == null) {
            throw new IllegalArgumentException("Enter valid vehicle type");
        }

        this.licensePlate = licensePlate;
        this.vehicleType = vehicleType;
    }


    public String getLicensePlate() {
        return licensePlate;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "licensePlate='" + licensePlate + '\'' +
                ", vehicleType=" + vehicleType +
                '}';
    }
}
