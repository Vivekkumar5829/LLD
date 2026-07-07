package com.booking.hotel.models;

import com.booking.hotel.enums.AmenityType;
import com.booking.hotel.enums.RoomType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class Room {
    private final String roomId;
    private final String roomNumber;
    private final RoomType roomType;
    private double pricePerNight;
    private final int capacity;
    private final List<AmenityType> amenities;
    private boolean isAvailable;

    public Room(String roomNumber, RoomType roomType,
                double pricePerNight, int capacity,
                List<AmenityType> amenities) {

        if (roomNumber == null || roomNumber.trim().isEmpty())
            throw new IllegalArgumentException(
                    "Room number can't be empty");
        if (roomType == null)
            throw new IllegalArgumentException(
                    "Room type can't be null");
        if (pricePerNight <= 0)
            throw new IllegalArgumentException(
                    "Price must be greater than 0");
        if (capacity <= 0)
            throw new IllegalArgumentException(
                    "Capacity must be greater than 0");

        this.roomId = UUID.randomUUID().toString();
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.pricePerNight = pricePerNight;
        this.capacity = capacity;
        this.amenities = amenities != null ?
                amenities : new ArrayList<>();
        this.isAvailable = true; // starts available
    }

    // Called when room is booked
    public void markUnavailable() {
        this.isAvailable = false;
    }

    // Called when booking is cancelled/completed
    public void markAvailable() {
        this.isAvailable = true;
    }

    public boolean isAvailable() { return isAvailable; }

    // Getters
    public String getRoomId() { return roomId; }
    public String getRoomNumber() { return roomNumber; }
    public RoomType getRoomType() { return roomType; }
    public double getPricePerNight() { return pricePerNight; }
    public int getCapacity() { return capacity; }
    public List<AmenityType> getAmenities() {
        return Collections.unmodifiableList(amenities);
    }

    @Override
    public String toString() {
        return "Room{" +
                "number='" + roomNumber + '\'' +
                ", type=" + roomType +
                ", price=" + pricePerNight +
                ", available=" + isAvailable +
                '}';
    }
}