package com.system.models;

import com.system.enums.ChannelType;
import com.system.enums.NotificationStatus;
import com.system.enums.Priority;
import com.system.state.NotificationState;

import java.time.LocalDateTime;
import java.util.UUID;

public class Notification {

    private final String notificationId;
    private final User user;
    private final NotificationTemplate template;
    private final ChannelType channelType;
    private final Priority priority;

    private NotificationStatus status;
    private final LocalDateTime createdAt;

    public Notification(
            User user,
            NotificationTemplate template,
            ChannelType channelType,
            Priority priority
    ) {
        this.notificationId = UUID.randomUUID().toString();
        this.user = user;
        this.template = template;
        this.channelType = channelType;
        this.priority = priority;
        this.status = NotificationStatus.PENDING;
        this.createdAt = LocalDateTime.now();
    }


    public String getNotificationId() {
        return notificationId;
    }

    public User getUser() {
        return user;
    }

    public NotificationTemplate getTemplate() {
        return template;
    }

    public ChannelType getChannelType() {
        return channelType;
    }

    public Priority getPriority() {
        return priority;
    }

    public NotificationStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }


    public void updateStatus(NotificationStatus status) {
        this.status = status;
    }

    // Add these fields to Notification.java
    private NotificationState currentState;

// Add to constructor
    this.currentState = new CreatedState();

    // Add these methods
    public void send() {
        currentState.send(this);
    }

    public void markDelivered() {
        currentState.markDelivered(this);
    }

    public void markFailed() {
        currentState.markFailed(this);
    }

    public void retry() {
        currentState.retry(this);
    }

    public void cancel() {
        currentState.cancel(this);
    }

    // Called by State objects only — package private
    public void setState(NotificationState newState) {
        this.currentState = newState;
    }


    @Override
    public String toString() {
        return "Notification{" +
                "notificationId='" + notificationId + '\'' +
                ", channelType=" + channelType +
                ", priority=" + priority +
                ", status=" + status +
                '}';
    }

}