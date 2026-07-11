package com.system.state;

import com.system.models.Notification;
import com.system.state.NotificationState;

public class DeliveredState
        implements NotificationState {
    @Override
    public void send(Notification notification) {
        throw new IllegalStateException(
                "Already delivered.");
    }

    @Override
    public void retry(Notification notification) {
        throw new IllegalStateException(
                "Already delivered. No retry needed.");
    }

    @Override
    public void markDelivered(
            Notification notification) {
        throw new IllegalStateException(
                "Already delivered.");
    }

    @Override
    public void markFailed(
            Notification notification) {
        throw new IllegalStateException(
                "Cannot fail delivered notification.");
    }

    @Override
    public void cancel(Notification notification) {
        throw new IllegalStateException(
                "Cannot cancel delivered notification.");
    }
}
