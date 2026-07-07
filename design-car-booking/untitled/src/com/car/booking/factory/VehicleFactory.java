package com.car.booking.factory;

import com.car.booking.model.Vehicle;
import com.car.booking.model.enums.VehicleType;

public class VehicleFactory {
    public static Vehicle createVehicle(String licensePlate, VehicleType vehicleType, String model) {

        if(licensePlate == null || licensePlate.trim().isEmpty()) {
            throw new IllegalArgumentException("License plate cannot be null or empty");
        }

        if(vehicleType == null) {
            throw new IllegalArgumentException("Vehicle type cannot be null");
        }

        switch (vehicleType) {
            case BIKE:
                return new Vehicle(VehicleType.BIKE, licensePlate, 1, model);
            case AUTO:
                return new Vehicle(VehicleType.AUTO, licensePlate, 3, model);
            case MINI:
                return new Vehicle(VehicleType.MINI,licensePlate,4, model);
            case SEDAN:
                return new Vehicle(VehicleType.SEDAN,licensePlate,4,model);
            case XL:
                return new Vehicle(VehicleType.XL, licensePlate, 7, model);
            default:
                throw new IllegalArgumentException("Unknown vehicle type : " + vehicleType);
        }
    }
}
