package com.system.state;

import com.system.enums.NotificationStatus;
import com.system.models.Notification;

public class PendingState
        implements NotificationState {
    @Override
    public void send(Notification notification) {
        System.out.println("Sending notification...");
        notification.updateStatus(
                NotificationStatus.SENDING);
        notification.setState(new SendingState());
    }

    @Override
    public void retry(Notification notification) {
        throw new IllegalStateException(
                "Cannot retry pending notification.");
    }

    @Override
    public void markDelivered(
            Notification notification) {
        throw new IllegalStateException(
                "Cannot deliver before sending.");
    }

    @Override
    public void markFailed(
            Notification notification) {
        System.out.println("Notification failed.");
        notification.updateStatus(
                NotificationStatus.FAILED);
        notification.setState(new FailedState());
    }

    @Override
    public void cancel(Notification notification) {
        System.out.println(
                "Notification cancelled.");
        notification.updateStatus(
                NotificationStatus.CANCELLED);
    }
}
