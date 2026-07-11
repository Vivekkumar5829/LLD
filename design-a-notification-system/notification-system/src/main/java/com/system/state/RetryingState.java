package com.system.state;

import com.system.enums.NotificationStatus;
import com.system.models.Notification;

public class RetryingState
        implements NotificationState {
    @Override
    public void send(Notification notification) {
        System.out.println(
                "Retry attempt — sending again...");
        notification.updateStatus(
                NotificationStatus.SENDING);
        notification.setState(new SendingState());
    }

    @Override
    public void retry(Notification notification) {
        throw new IllegalStateException(
                "Already retrying.");
    }

    @Override
    public void markDelivered(
            Notification notification) {
        System.out.println(
                "Retry succeeded. Delivered.");
        notification.updateStatus(
                NotificationStatus.DELIVERED);
        notification.setState(
                new DeliveredState());
    }

    @Override
    public void markFailed(
            Notification notification) {
        System.out.println(
                "Retry also failed.");
        notification.updateStatus(
                NotificationStatus.FAILED);
        notification.setState(new FailedState());
    }

    @Override
    public void cancel(Notification notification) {
        System.out.println(
                "Retry cancelled.");
        notification.updateStatus(
                NotificationStatus.CANCELLED);
    }
}
