package com.system.strategy;


import com.system.models.Notification;

public class WhatsAppChannel implements NotificationChannel {

    @Override
    public void send(Notification notification) {

        System.out.println(
                "Sending WhatsApp notification to user: "
                        + notification.getUser().getName()
        );

        System.out.println(
                "Message: "
                        + notification.getTemplate().getContent()
        );
    }
}
