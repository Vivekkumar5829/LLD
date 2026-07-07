package com.parkinglot.factory;

import com.parkinglot.enums.VehicleType;
import com.parkinglot.model.Bike;
import com.parkinglot.model.Car;
import com.parkinglot.model.Truck;
import com.parkinglot.model.Vehicle;

public class VehicleFactory {
    public static Vehicle createVehicle(
            String licensePlate,
            VehicleType type) {
        if (licensePlate == null ||
                licensePlate.trim().isEmpty())
            throw new IllegalArgumentException(
                    "License plate cannot be empty");
        if (type == null)
            throw new IllegalArgumentException(
                    "Vehicle type cannot be null");

        switch (type) {
            case BIKE:  return new Bike(licensePlate);
            case CAR:   return new Car(licensePlate);
            case TRUCK: return new Truck(licensePlate);
            default:
                throw new IllegalArgumentException(
                        "Unsupported vehicle type: " + type);
        }
    }
}
