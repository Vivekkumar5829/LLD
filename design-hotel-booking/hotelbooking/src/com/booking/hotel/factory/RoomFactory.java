package com.booking.hotel.factory;

import com.booking.hotel.enums.AmenityType;
import com.booking.hotel.enums.RoomType;
import com.booking.hotel.models.Room;

import java.util.List;

public class RoomFactory {
    public static Room createRoom(RoomType type, String roomNumber) {
        if(type == null) {
            throw new IllegalArgumentException(
                    "Room type can't be null");
        }

        if(roomNumber == null || roomNumber.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Room number can't be empty");
        }

        switch (type) {
            case STANDARD :
                return new Room(roomNumber, RoomType.STANDARD, 1500, 2, List.of(AmenityType.WIFI));
            case AC:
                return new Room(roomNumber, RoomType.AC, 2000, 2, List.of(AmenityType.WIFI, AmenityType.BREAKFAST, AmenityType.DINNER));
            case SUITE:
                return new Room(roomNumber, RoomType.SUITE, 2500, 3, List.of(AmenityType.WIFI, AmenityType.BREAKFAST, AmenityType.LUNCH, AmenityType.DINNER));
            case DUPLEX:
                return new Room(roomNumber, RoomType.DUPLEX, 3000, 3, List.of(AmenityType.WIFI, AmenityType.BREAKFAST, AmenityType.LUNCH, AmenityType.DINNER, AmenityType.GYM, AmenityType.POOL));
            default:
                throw new IllegalArgumentException("Unsupported room type");
        }
     }
}
