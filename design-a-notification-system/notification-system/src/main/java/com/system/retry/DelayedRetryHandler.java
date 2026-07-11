package com.system.retry;

import com.system.models.Notification;

public class DelayedRetryHandler extends RetryHandler {


    @Override
    public void retry(Notification notification, int attempt) {

        if(attempt <= 3){

            System.out.println(
                    "Delayed retry attempt "
                            + attempt
                            + " for notification "
                            + notification.getNotificationId()
            );

        } else {

            System.out.println(
                    "Retry exhausted"
            );
        }
    }
}