package com.booking.hotel.models;

import com.booking.hotel.enums.AmenityType;
import com.booking.hotel.enums.RoomType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class Hotel {
    private final String hotelId;
    private final String name;
    private final Address address;
    private final List<Room> rooms;
    private final List<AmenityType> amenities;
    private final int starRating;

    public Hotel(String name, Address address,
                 List<AmenityType> amenities,
                 int starRating) {
        if (name == null || name.trim().isEmpty())
            throw new IllegalArgumentException(
                    "Hotel name cannot be empty");
        if (address == null)
            throw new IllegalArgumentException(
                    "Address cannot be null");
        if (starRating < 1 || starRating > 5)
            throw new IllegalArgumentException(
                    "Star rating must be between 1 and 5");

        this.hotelId = UUID.randomUUID().toString();
        this.name = name;
        this.address = address;
        this.rooms = new ArrayList<>();
        this.amenities = amenities != null ?
                amenities : new ArrayList<>();
        this.starRating = starRating;
    }

    // Add room to hotel
    public void addRoom(Room room) {
        if (room == null)
            throw new IllegalArgumentException(
                    "Room cannot be null");
        rooms.add(room);
    }

    // Remove room by roomId
    public void removeRoom(String roomId) {
        rooms.removeIf(r -> r.getRoomId().equals(roomId));
    }

    // Core method — finds available rooms by type
    public List<Room> getAvailableRooms(RoomType type) {
        List<Room> available = new ArrayList<>();
        for (Room room : rooms) {
            if (room.isAvailable() &&
                    room.getRoomType() == type) {
                available.add(room);
            }
        }
        return available;
    }

    // Get ALL rooms regardless of availability
    public List<Room> getAllRooms() {
        return Collections.unmodifiableList(rooms);
    }

    public String getHotelId() { return hotelId; }
    public String getName() { return name; }
    public Address getAddress() { return address; }
    public int getStarRating() { return starRating; }
    public List<AmenityType> getAmenities() {
        return Collections.unmodifiableList(amenities);
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "name='" + name + '\'' +
                ", address=" + address +
                ", stars=" + starRating +
                '}';
    }
}