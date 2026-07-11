package com.system.state;

import com.system.models.Notification;

public interface NotificationState {
    void send(Notification notification);
    void retry(Notification notification);
    void markDelivered(Notification notification);
    void markFailed(Notification notification);
    void cancel(Notification notification);
}
