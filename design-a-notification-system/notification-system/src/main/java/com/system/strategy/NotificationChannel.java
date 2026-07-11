package com.system.strategy;


import com.system.models.Notification;

public interface NotificationChannel {

    void send(Notification notification);
}