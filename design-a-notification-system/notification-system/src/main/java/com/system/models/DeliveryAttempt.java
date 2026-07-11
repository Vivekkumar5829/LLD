package com.system.models;

import com.system.enums.NotificationStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public class DeliveryAttempt {

    private final String attemptId;
    private final String notificationId;

    private final int attemptNumber;
    private NotificationStatus status;

    private String failureReason;
    private final LocalDateTime attemptedAt;


    public DeliveryAttempt(
            String notificationId,
            int attemptNumber
    ) {
        this.attemptId = UUID.randomUUID().toString();
        this.notificationId = notificationId;
        this.attemptNumber = attemptNumber;
        this.status = NotificationStatus.PENDING;
        this.attemptedAt = LocalDateTime.now();
    }


    public String getAttemptId() {
        return attemptId;
    }

    public String getNotificationId() {
        return notificationId;
    }

    public int getAttemptNumber() {
        return attemptNumber;
    }

    public NotificationStatus getStatus() {
        return status;
    }

    public String getFailureReason() {
        return failureReason;
    }


    public void markSuccess() {
        this.status = NotificationStatus.DELIVERED;
    }


    public void markFailure(String reason) {
        this.status = NotificationStatus.FAILED;
        this.failureReason = reason;
    }
}