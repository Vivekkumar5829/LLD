package com.car.booking.model;

public class Location {
    private double latitude;
    private double longitude;

    public Location(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double distanceTo(Location other) {
        double latDiff = other.latitude - this.latitude;
        double lonDiff = other.longitude - this.longitude;

        return Math.sqrt(latDiff * latDiff + lonDiff * lonDiff);

    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    @Override
    public String toString() {
        return "(" + latitude + ", " + longitude + ")";
    }
}
