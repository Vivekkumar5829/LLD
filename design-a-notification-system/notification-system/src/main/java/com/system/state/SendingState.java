package com.system.state;

import com.system.enums.NotificationStatus;
import com.system.models.Notification;

public class SendingState
        implements NotificationState {
    @Override
    public void send(Notification notification) {
        throw new IllegalStateException(
                "Already sending.");
    }

    @Override
    public void retry(Notification notification) {
        throw new IllegalStateException(
                "Cannot retry while sending.");
    }

    @Override
    public void markDelivered(
            Notification notification) {
        System.out.println(
                "Notification delivered successfully.");
        notification.updateStatus(
                NotificationStatus.DELIVERED);
        notification.setState(
                new DeliveredState());
    }

    @Override
    public void markFailed(
            Notification notification) {
        System.out.println(
                "Sending failed. Moving to FAILED.");
        notification.updateStatus(
                NotificationStatus.FAILED);
        notification.setState(new FailedState());
    }

    @Override
    public void cancel(Notification notification) {
        throw new IllegalStateException(
                "Cannot cancel while sending.");
    }
}

