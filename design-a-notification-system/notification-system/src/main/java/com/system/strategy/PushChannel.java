package com.system.strategy;

import com.system.models.Notification;

public class PushChannel implements NotificationChannel {

    @Override
    public void send(Notification notification) {

        System.out.println(
                "Sending Push notification to user: "
                        + notification.getUser().getName()
        );

        System.out.println(
                "Message: "
                        + notification.getTemplate().getContent()
        );
    }
}
