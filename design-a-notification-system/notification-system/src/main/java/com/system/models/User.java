package com.system.models;

import java.util.UUID;

public class User {

    private final String userId;
    private final String name;
    private final NotificationPreference notificationPreference;

    public User(String name, NotificationPreference notificationPreference) {
        this.userId = UUID.randomUUID().toString();
        this.name = name;
        this.notificationPreference = notificationPreference;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public NotificationPreference getNotificationPreference() {
        return notificationPreference;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}