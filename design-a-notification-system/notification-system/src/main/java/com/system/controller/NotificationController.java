package com.system.controller;


import com.system.models.Notification;
import com.system.service.NotificationService;


public class NotificationController {


    private final NotificationService notificationService;


    public NotificationController(){

        this.notificationService =
                NotificationService.getInstance();
    }



    public void send(Notification notification){

        notificationService.sendNotification(notification);
    }
}