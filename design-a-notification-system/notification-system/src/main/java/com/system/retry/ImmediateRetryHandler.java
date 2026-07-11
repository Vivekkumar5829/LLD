package com.system.retry;

import com.system.models.Notification;

public class ImmediateRetryHandler extends RetryHandler {

    @Override
    public void retry(Notification notification, int attempt) {

        if(attempt <= 1) {

            System.out.println(
                    "Immediate retry for notification: "
                            + notification.getNotificationId()
            );

        } else if(nextHandler != null){

            nextHandler.retry(notification, attempt);
        }
    }
}