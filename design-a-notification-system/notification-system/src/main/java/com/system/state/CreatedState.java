package com.system.state;


import com.system.enums.NotificationStatus;
import com.system.models.Notification;

public class CreatedState
            implements NotificationState {


        @Override
        public void send(Notification notification) {
            System.out.println("Moving to PENDING...");
            notification.updateStatus(
                    NotificationStatus.PENDING);
            notification.setState(new PendingState());
        }

        @Override
        public void retry(Notification notification) {
            throw new IllegalStateException(
                    "Cannot retry. Not sent yet.");
        }

        @Override
        public void markDelivered(
                Notification notification) {
            throw new IllegalStateException(
                    "Cannot deliver. Not sent yet.");
        }

        @Override
        public void markFailed(
                Notification notification) {
            throw new IllegalStateException(
                    "Cannot fail. Not sent yet.");
        }

        @Override
        public void cancel(Notification notification) {
            System.out.println(
                    "Notification cancelled before sending.");
            notification.updateStatus(
                    NotificationStatus.CANCELLED);
        }
    }

