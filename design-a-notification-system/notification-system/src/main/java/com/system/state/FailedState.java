package com.system.state;

import com.system.enums.NotificationStatus;
import com.system.models.Notification;
import com.system.state.NotificationState;

public class FailedState
        implements NotificationState {
    @Override
    public void send(Notification notification) {
        throw new IllegalStateException(
                "Cannot send failed notification. Retry first.");
    }

    @Override
    public void retry(Notification notification) {
        System.out.println(
                "Retrying notification...");
        notification.updateStatus(
                NotificationStatus.RETRYING);
        notification.setState(
                new RetryingState());
        // Triggers retry — back to pending
        notification.send();
    }

    @Override
    public void markDelivered(
            Notification notification) {
        throw new IllegalStateException(
                "Cannot deliver failed notification.");
    }

    @Override
    public void markFailed(
            Notification notification) {
        throw new IllegalStateException(
                "Already failed.");
    }

    @Override
    public void cancel(Notification notification) {
        System.out.println(
                "Failed notification cancelled.");
        notification.updateStatus(
                NotificationStatus.CANCELLED);
    }
}
